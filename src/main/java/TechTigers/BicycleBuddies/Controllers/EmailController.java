package TechTigers.BicycleBuddies.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @RequestMapping("/send-email")
    public String sendEmail(){
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("bicyclebuddies8080@gmail.com");
            message.setTo("bicyclebuddies8080@gmail.com");
            message.setSubject("Please Verify Your Account");
            message.setText("Test Email");

            mailSender.send(message);
            return "success";
        } catch (Exception e){
            return e.getMessage();
        }

    }

}
