package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.EmailVerificationFormDTO;
import TechTigers.BicycleBuddies.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("login-verification")
public class LoginVerifyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final String USER_SESSION_KEY = "user";

    private User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(USER_SESSION_KEY);
        return (userId != null) ? userRepository.findById(userId).orElse(null) : null;
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(USER_SESSION_KEY, user.getId());
    }

    private final JavaMailSender mailSender;

    public LoginVerifyController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("")
    public String sendVerificationEmail(HttpServletRequest request) {
        User user = getUserFromSession(request.getSession());

        if (user == null) {
            return "redirect:/login";  // ✅ Redirect if user not found
        }

        String userEmail = user.getEmail();

        // ✅ Generate and set verification code
        int verificationCode = User.generateToken();
        user.setEmailVerificationCode(verificationCode);
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bicyclebuddies8080@gmail.com");
        message.setTo(userEmail);
        message.setSubject("Login Code from Bicycle Buddies");
        message.setText("Your login code is: " + verificationCode);
//        mailSender.send(message);

        return "redirect:/login-verification/login-email-sent";
    }

    @GetMapping("login-email-sent")
    public String displayVerifyForm(Model model) {
        model.addAttribute(new EmailVerificationFormDTO());
        return "login-verification/login-email-sent";
    }

    @PostMapping("login-email-sent")
    public String processVerifyForm(@ModelAttribute @Valid EmailVerificationFormDTO emailVerificationFormDTO,
                                    Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login-verification/login-email-sent";
        }

        User user = getUserFromSession(request.getSession());
        if (user == null) {
            return "redirect:/login";
        }

        int userSubmittedCode = emailVerificationFormDTO.getUserSubmittedEmailVerification();
        int storedVerificationCode = user.getEmailVerificationCode(); // ✅ Ensure getter exists

        if (storedVerificationCode != userSubmittedCode) {
            errors.rejectValue("userSubmittedEmailVerification", "userSubmittedEmailVerification.incorrect", "Verification code does not match");
            return "login-verification/login-email-sent";
        }

        setUserInSession(request.getSession(), user);
        return "redirect:/home";
    }
}
