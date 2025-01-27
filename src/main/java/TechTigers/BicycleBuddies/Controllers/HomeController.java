package TechTigers.BicycleBuddies.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home-logged-out")
    public String homePageLoggedOut(){
        return "home-logged-out";
    }

    @GetMapping("/home-logged-in")
    public String homePageLoggedIn(){
        return "home-logged-in";
    }
}
