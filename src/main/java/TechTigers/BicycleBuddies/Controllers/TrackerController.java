package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.MilesTrackerService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("tracker")
public class TrackerController {
    private final MilesTrackerService milesTrackerService;
    private final UserService userService;

    @Autowired
    public TrackerController(MilesTrackerService milesTrackerService, UserService userService) {
        this.milesTrackerService = milesTrackerService;
        this.userService = userService;
    }

    @GetMapping("index/{userId}")
    public String displayTracker(Model model, @PathVariable int userId) {
        Optional<User> optionalUser = userService.getProfileById(userId);
        if (optionalUser.isPresent()) {
            User user = (User) optionalUser.get();

            model.addAttribute("title", user.getUserName() + " Miles Tracker");
            model.addAttribute("numberOfRides", milesTrackerService.totalNumRides());
            return "/tracker/index";
        } else {

            return "redirect:/";
        }
    }
}
