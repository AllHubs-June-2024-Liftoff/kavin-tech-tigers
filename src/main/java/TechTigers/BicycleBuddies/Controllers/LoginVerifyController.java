package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
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
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private static final String userSessionKey = "user";

    // Method to retrieve the user from the session
    public User getUserFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(userSessionKey); // Use Long for user ID
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        return user.orElse(null);
    }

    // Method to set the user in the session
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    private final JavaMailSender mailSender;

    // Constructor injection for mail sender
    public LoginVerifyController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Handle the request to send the verification email
    @GetMapping("")
    public String sendSMS(HttpServletRequest request) {
        User user = getUserFromSession(request.getSession());
        if (user == null) {
            return "redirect:/login"; // Redirect if no user is found
        }

        String userEmail = user.getEmail();

        // Generate new token upon every login attempt
        user.setEmailVerificationCode(User.generateToken()); // Generate and set verification code
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bicyclebuddies8080@gmail.com");
        message.setTo(userEmail);
        message.setSubject("Login Code from Bicycle Buddies");
        message.setText("Your login code is " + user.getEmailVerificationCode());

        mailSender.send(message);

        return "redirect:/login-verification/login-email-sent";
    }

    // Display the form for entering the email verification code
    @GetMapping("login-email-sent")
    public String displayVerifyForm(Model model) {
        model.addAttribute(new EmailVerificationFormDTO());
        return "login-verification/login-email-sent";
    }

    // Handle the form submission for verifying the email
    @PostMapping("login-email-sent")
    public String processVerifyForm(@ModelAttribute @Valid EmailVerificationFormDTO emailVerificationFormDTO,
                                    Errors errors, HttpServletRequest request,
                                    Model model) {

        if (errors.hasErrors()) {
            return "login-verification/login-email-sent";
        }

        User user = getUserFromSession(request.getSession());
        if (user == null) {
            return "redirect:/login"; // Handle case where user is not found in session
        }

        int userSubmittedEmailVerification = emailVerificationFormDTO.getUserSubmittedEmailVerification();
        int userGivenEmailVerification = user.getEmailVerificationCode();

        // Compare the submitted code with the generated code
        if (userGivenEmailVerification != userSubmittedEmailVerification) {
            errors.rejectValue("userSubmittedSMSVerification", "userSubmittedSMSVerification.incorrect", "Verification code does not match");
            return "login-verification/login-email-sent";
        }

        setUserInSession(request.getSession(), user); // Set user in session if successful
        return "redirect:/map"; // Redirect to map or homepage after successful verification
    }
}
