package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.VerifyFormDTO;
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
@RequestMapping("email")
public class EmailController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    // Method to get the User object from the session
    public User getUserFromSession(HttpSession session){
        Long userId = (Long) session.getAttribute(userSessionKey); // Change to Long
        if (userId == null){
            return null;
        }

        Optional<User> user = userRepository.findById(userId); // Updated to Long

        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }

    // Method to set the User object in the session
    private static void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    private final JavaMailSender mailSender;

    // Constructor to inject JavaMailSender
    public EmailController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    // Method to send the verification email
    @GetMapping("")
    public String sendEmail(HttpServletRequest request){

        User user = getUserFromSession(request.getSession());
        String userEmail = user.getEmail();
        int userVerifyCode = user.getVerificationCode();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bicyclebuddies8080@gmail.com");
            message.setTo(userEmail);
            message.setSubject("Please Verify Your Account");
            message.setText("Your verification code is " + userVerifyCode + ". Use this code to finish setting up your account.");

            mailSender.send(message);

            return "redirect:/email/verification-email-sent";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    // Method to display the verification form after sending the email
    @GetMapping("verification-email-sent")
    public String displayVerifyForm(Model model){
        model.addAttribute(new VerifyFormDTO());
        return "email/verification-email-sent";
    }

    // Method to process the verification form
    @PostMapping("verification-email-sent")
    public String processVerifyForm(@ModelAttribute @Valid VerifyFormDTO verifyFormDTO,
                                    Errors errors, HttpServletRequest request,
                                    Model model){

        if(errors.hasErrors()){
            return "email/verification-email-sent";
        }

        User user = getUserFromSession(request.getSession());
        int userSubmittedVerification = verifyFormDTO.getUserSubmittedVerification();
        int userGivenVerification = user.getVerificationCode();

        if(userGivenVerification != userSubmittedVerification){
            errors.rejectValue("userSubmittedVerification", "userSubmittedVerification.incorrect", "Verification code does not match");
            return "email/verification-email-sent";
        } else {
            user.setVerified(true);
            userRepository.save(user); // Save the updated user with verification status
        }

        return "redirect:/login"; // Redirect to login page after successful verification
    }
}
