package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.SMSSendRequest;
import TechTigers.BicycleBuddies.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static TechTigers.BicycleBuddies.models.User.generateToken;

@RestController
@RequestMapping("/api/v1")
public class SMSRestController {

    @Autowired
    SMSService smsService;

    @GetMapping("/processSMS")
    public String processSMS(){
        return "todo";
    }

    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest sendRequest){
        sendRequest.setSmsMessage("Your login code is " + generateToken());
        sendRequest.setDestinationSMSNumber("+16363687378");
        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(), sendRequest.getSmsMessage());
    }

}
