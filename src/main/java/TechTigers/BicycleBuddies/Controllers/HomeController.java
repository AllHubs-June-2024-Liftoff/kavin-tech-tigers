package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final String userSessionKey = "user";

    @GetMapping("")
    public String index(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if(user == null){
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String homePageLoggedOut(){
        return "home";
    }
}