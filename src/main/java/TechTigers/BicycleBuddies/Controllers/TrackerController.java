package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tracker")
public class TrackerController {

    private final UserService userService;

    @Autowired
    public TrackerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/tracker/{id}")
    public String viewTracker(@PathVariable int id, Model model) {
        Optional<User> userOptional = userService.getProfileById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("username", user.getUsername()); // Corrected method
            return "tracker";
        } else {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }
}
