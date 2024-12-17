package TechTigers.BicycleBuddies.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String displayRegistrationForm(){
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(){
        return "register";
    }

}