package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.SMSSendRequest;
//import TechTigers.BicycleBuddies.service.SMSService;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import static TechTigers.BicycleBuddies.models.User.generateToken;

@RestController
@RequestMapping("sms-verification")
public class SMSController {

//    @Autowired
//    SMSService smsService;

    @GetMapping("")
    public String sendSMS(@RequestBody SMSSendRequest sendRequest){
        final String ACCOUNT_SID = System.getenv("twilio_account_sid");
        final String AUTH_TOKEN = System.getenv("twilio_auth_token");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //First phone number is the TO the second phone number is the FROM
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+16363687378"),
                        new com.twilio.type.PhoneNumber("+16363687378"),
                        "Your login code is " + generateToken())
                        .create();
//        return message.getBody();
        return "redirect:/sms-verification/sent";

//        sendRequest.setSmsMessage("Your login code is " + generateToken());
//        sendRequest.setDestinationSMSNumber("+16363687378");
//        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(), sendRequest.getSmsMessage());
    }

}
