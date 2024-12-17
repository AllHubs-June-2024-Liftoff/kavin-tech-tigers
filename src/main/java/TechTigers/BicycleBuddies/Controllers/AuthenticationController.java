package TechTigers.BicycleBuddies.controllers;

import TechTigers.BicycleBuddies.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String displayRegistrationForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(){
        return "register";
    }

}