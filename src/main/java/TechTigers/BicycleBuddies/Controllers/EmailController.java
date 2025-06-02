package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
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

    public EmailController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("")
    public String sendEmail(HttpServletRequest request){

        //Commented out to remove validation for testing purposes
        User user = getUserFromSession(request.getSession());
        String userEmail = user.getEmail();
        int userVerifyCode = user.getVerificationCode();
//
//        //Your antivirus might throw an error here
        try {
            SimpleMailMessage message = new SimpleMailMessage();


            message.setFrom("bicyclebuddies8080@gmail.com");
            //Message may be sent to spam folder
            message.setTo(userEmail);
            message.setSubject("Please Verify Your Account");
            message.setText("Your verification code is " + userVerifyCode + ". Use this code to finish setting up your account.");

            mailSender.send(message);

            return "redirect:/email/verification-email-sent";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("verification-email-sent")
    public String displayVerifyForm(Model model){
        model.addAttribute(new VerifyFormDTO());
        return "email/verification-email-sent";
    }

    @PostMapping("verification-email-sent")
    public String processVerifyForm(@ModelAttribute @Valid VerifyFormDTO verifyFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model, RegisterFormDTO registerFormDTO){

        //Commented out to remove validation for testing purposes
        if(errors.hasErrors()){
            return "email/verification-email-sent";
        }

        User user = getUserFromSession(request.getSession());
        int userSubmittedVerification = verifyFormDTO.getUserSubmittedVerification();
        int userGivenVerification = user.getVerificationCode();

        if(userGivenVerification != userSubmittedVerification){
            errors.rejectValue("userSubmittedVerification", "userSubmittedVerification.incorrect", "Verification code does not match");
            return "email/verification-email-sent";
        }else{
            user.setVerified(true);
            userRepository.save(user);
            setUserInSession(request.getSession(), user);
        }

        return "redirect:/login";
        //TODO:Redirect user to Edit Profile after verifying account
//        return "redirect:/profile/profile";
    }

}
