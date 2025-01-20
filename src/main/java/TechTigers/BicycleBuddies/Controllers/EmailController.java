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

@Controller
@RequestMapping("verification-email-sent")
public class EmailController {

    @Autowired
    UserRepository userRepository;

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public String sendEmail(){
        //This will not work if you have anitvirus turned on
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("bicyclebuddies8080@gmail.com");
            message.setTo("bicyclebuddies8080@gmail.com");
            message.setSubject("Please Verify Your Account");
            message.setText("Test Email");

            mailSender.send(message);
            return "verification-email-sent";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("")
    public String displayVerificationForm(Model model){
        model.addAttribute(new VerifyFormDTO());
        return "verification-email-sent";
    }

//    @PostMapping("/")
//    public String processVerifyForm(@ModelAttribute @Valid VerifyFormDTO verifyFormDTO,
//                                          Errors errors, HttpServletRequest request,
//                                          Model model, RegisterFormDTO registerFormDTO){
//
//        if(errors.hasErrors()){
//            return "verification-email-sent";
//        }
//
//        int userSubmittedVerification = userRepository.findByUsername((registerFormDTO.getUsername()).get);
//        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
//
//        if(existingUser != null){
//            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        String password = registerFormDTO.getPassword();
//        String verifyPassword = registerFormDTO.getVerifyPassword();
//        if(!password.equals(verifyPassword)){
//            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
//        userRepository.save(newUser);
//
//        return "redirect:/login";
//    }

}
