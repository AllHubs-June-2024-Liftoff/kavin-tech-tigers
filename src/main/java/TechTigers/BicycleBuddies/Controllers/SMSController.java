package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.SMSSendRequest;
//import TechTigers.BicycleBuddies.service.SMSService;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
import TechTigers.BicycleBuddies.models.dto.SMSVerificaitonFormDTO;
import TechTigers.BicycleBuddies.models.dto.VerifyFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("sms-verification")
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

    @GetMapping("")
    public String sendSMS(HttpServletRequest request){

        User user = getUserFromSession(request.getSession());
        user.setSmsVerificationCode(generateToken());

        final String ACCOUNT_SID = System.getenv("twilio_account_sid");
        final String AUTH_TOKEN = System.getenv("twilio_auth_token");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        //First phone number is the TO the second phone number is the FROM
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+16363687378"),
                        new com.twilio.type.PhoneNumber("+16363687378"),
                        "Your login code is " + user.getSmsVerificationCode())
                        .create();
//        return message.getBody();
        return "redirect:/sms-verification/sent";

//        sendRequest.setSmsMessage("Your login code is " + generateToken());
//        sendRequest.setDestinationSMSNumber("+16363687378");
//        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(), sendRequest.getSmsMessage());
    }

    @GetMapping("sent")
    public String displayVerifyForm(Model model){
        model.addAttribute(new SMSVerificaitonFormDTO());
        return "sms-verification/sent";
    }

    @PostMapping("sent")
    public String processVerifyForm(@ModelAttribute @Valid SMSVerificaitonFormDTO smsVerificaitonFormDTO,
                                    Errors errors, HttpServletRequest request,
                                    Model model, RegisterFormDTO registerFormDTO){

        if(errors.hasErrors()){
            return "sms-verification/sent";
        }

        User user = getUserFromSession(request.getSession());
        int userSubmittedSMSVerification = smsVerificaitonFormDTO.getUserSubmittedSMSVerification();
        int userGivenSMSVerification = user.getSmsVerificationCode();

        if(userGivenSMSVerification != userSubmittedSMSVerification){
            errors.rejectValue("userSubmittedSMSVerification", "userSubmittedSMSVerification.incorrect", "Verification code does not match");
            return "email/verification-email-sent";
        }else{
            user.setVerified(true);
            userRepository.save(user);
        }

        return "redirect:/login";
    }

}
