package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
//import TechTigers.BicycleBuddies.service.SMSService;
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

import static TechTigers.BicycleBuddies.models.User.generateToken;

@Controller
@RequestMapping("login-verification")
public class LoginVerifyController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session){
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null){
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    private final JavaMailSender mailSender;

    public LoginVerifyController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("")
    public String sendSMS(HttpServletRequest request){

//        User user = getUserFromSession(request.getSession());
//        String userEmail = user.getEmail();

        //generates new token upon every login attempt
//        user.setEmailVerificationCode(generateToken());
//        userRepository.save(user);
//        int userVerifyCode = user.getEmailVerificationCode();

//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("bicyclebuddies8080@gmail.com");
        //Message may be sent to spam folder
//        message.setTo(userEmail);
//        message.setSubject("Login Code from Bicycle Buddies");
//        message.setText("Your login code is " + userVerifyCode);

//        mailSender.send(message);

        return "redirect:/login-verification/login-email-sent";

//
    }

    @GetMapping("login-email-sent")
    public String displayVerifyForm(Model model){
        model.addAttribute(new EmailVerificationFormDTO());
        return "login-verification/login-email-sent";
    }

    @PostMapping("login-email-sent")
    public String processVerifyForm(@ModelAttribute @Valid EmailVerificationFormDTO emailVerificationFormDTO,
                                    Errors errors, HttpServletRequest request,
                                    Model model, RegisterFormDTO registerFormDTO){

        if(errors.hasErrors()){
            return "login-verification/login-email-sent";
        }

        User user = getUserFromSession(request.getSession());
        //Commented out to remove validation for testing purposes
//        int userSubmittedEmailVerification = emailVerificationFormDTO.getUserSubmittedEmailVerification();
//        int userGivenEmailVerification = user.getEmailVerificationCode();

//        if(userGivenEmailVerification != userSubmittedEmailVerification){
//            errors.rejectValue("userSubmittedSMSVerification", "userSubmittedSMSVerification.incorrect", "Verification code does not match");
//            return "login-verification/login-email-sent";
//        }

        setUserInSession(request.getSession(), user);
//        int profileId = user.getId();
//        userService.getProfileById(profileId);
        return "redirect:/home";
    }

}
