package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.SMSSendRequest;
import TechTigers.BicycleBuddies.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static TechTigers.BicycleBuddies.models.User.generateToken;

@RestController
@RequestMapping("sms-verification")
public class SMSRestController {

    @Autowired
    SMSService smsService;

    @GetMapping("")
    public String processSMS(){
        return "todo";
    }

    @PostMapping("")
    public String processSMS(@RequestBody SMSSendRequest sendRequest){
        sendRequest.setSmsMessage("Your login code is " + generateToken());
        sendRequest.setDestinationSMSNumber("+16363687378");
        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(), sendRequest.getSmsMessage());
    }

}
