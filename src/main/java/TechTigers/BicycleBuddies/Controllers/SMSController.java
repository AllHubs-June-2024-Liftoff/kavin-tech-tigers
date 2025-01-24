package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
//import TechTigers.BicycleBuddies.service.SMSService;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
import TechTigers.BicycleBuddies.models.dto.EmailVerificationFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import javax.validation.Valid;

import java.util.Optional;

import static TechTigers.BicycleBuddies.models.User.generateToken;

@Controller
@RequestMapping("email-verification")
public class SMSController {

    @Autowired
    UserRepository userRepository;

    //    @Autowired
//    SMSService smsService;

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

    public SMSController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("")
    public String sendSMS(HttpServletRequest request){

        User user = getUserFromSession(request.getSession());
        String userEmail = user.getEmail();
        user.setEmailVerificationCode(generateToken());
        int userVerifyCode = user.getEmailVerificationCode();


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bicyclebuddies8080@gmail.com");
        //Message may be sent to spam folder
        message.setTo(userEmail);
        message.setSubject("Login Code from Bicycle Buddies");
        message.setText("Your login code is " + userVerifyCode);

        mailSender.send(message);

        return "redirect:/email/verification-email-sent";

//
    }

    @GetMapping("sent")
    public String displayVerifyForm(Model model){
        model.addAttribute(new EmailVerificationFormDTO());
        return "sms-verification/sent";
    }

    @PostMapping("sent")
    public String processVerifyForm(@ModelAttribute @Valid EmailVerificationFormDTO emailVerificaitonFormDTO,
                                    Errors errors, HttpServletRequest request,
                                    Model model, RegisterFormDTO registerFormDTO){

        if(errors.hasErrors()){
            return "sms-verification/sent";
        }

        User user = getUserFromSession(request.getSession());
        int userSubmittedEmailVerification = emailVerificaitonFormDTO.getUserSubmittedEmailVerification();
        int userGivenEmailVerification = user.getEmailVerificationCode();

        if(userGivenEmailVerification != userSubmittedEmailVerification){
            errors.rejectValue("userSubmittedSMSVerification", "userSubmittedSMSVerification.incorrect", "Verification code does not match");
            return "email/verification-email-sent";
        }else{
            user.setVerified(true);
            userRepository.save(user);
        }

        return "redirect:/login";
    }

}
