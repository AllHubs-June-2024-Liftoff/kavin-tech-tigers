package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService; // Use UserService instead of AuthenticationController

    private static final String userSessionKey = "user";

    @GetMapping("")
    public String index(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = userService.getUserFromSession(session); // Use userService instead of authenticationController

        if (user == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String homePageLoggedOut() {
        return "home";
    }
}
