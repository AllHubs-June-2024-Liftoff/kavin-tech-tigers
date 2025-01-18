package TechTigers.BicycleBuddies.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

//    @Autowired
//    private JavaMailSender mailSender;

    @RequestMapping("/send-email")
    public String sendEmail(){

    }

}
