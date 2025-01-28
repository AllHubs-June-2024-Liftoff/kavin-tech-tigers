package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.MilesTracker;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.MilesTrackerService;
import TechTigers.BicycleBuddies.service.RideService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("tracker")
public class TrackerController {
    private final MilesTrackerService milesTrackerService;
    private final UserService userService;
    private final RideService rideService;

    @Autowired
    public TrackerController(MilesTrackerService milesTrackerService, UserService userService, RideService rideService) {
        this.milesTrackerService = milesTrackerService;
        this.userService = userService;
        this.rideService = rideService;
    }


    @GetMapping("/all-tracker/{userId}")
    public String viewAllTracking(Model model, @PathVariable int userId){
        Optional<User> user = userService.getProfileById(userId);
        if(user.isEmpty()){
            model.addAttribute("error", "User not found");
            return "tracker/all-tracking";
        }
        List<MilesTracker> milesTracker = milesTrackerService.getTrackingByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("milesTracker", milesTracker);
        model.addAttribute("title", "All Tracking");
        return "tracker/all-tracking";
    }

    @GetMapping("/add-tracker/{userId}")
    public String showAddTrackingForm(@PathVariable int userId, Model model){
        Optional<User> optionalUser = userService.getProfileById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("tracking", new MilesTracker());
            model.addAttribute("title", "Add to Tracker");
        } else{
            model.addAttribute("error", "tracker not found");
        }
        return "tracker/add-tracking";
    }

    @PostMapping("/add-tracker/{userId}/add")
    public String addTracking(@PathVariable int userId, @ModelAttribute MilesTracker milesTracker, Model model){
        Optional<Optional<User>> user = Optional.ofNullable(userService.getProfileById(userId));
        if(user.isEmpty()){
            model.addAttribute("error", "user is not found");
            return "redirect:/tracker/all-tracking";
        }
        milesTrackerService.saveMilesTracker(milesTracker);
        return "redirect:/tracker/all-tracking";
    }


    @GetMapping("/add-tracker/{userId}/add")
    public String redirectToAddTrackingForm(@PathVariable int userId) {
        return "redirect:/tracker/add-tracking/" + userId;
    }

    @DeleteMapping("/delete/{trackerId}")
    public String deleteTracker(@PathVariable int trackerId) {
        milesTrackerService.deleteTracker(trackerId);
        return "redirect:/tracker/all-tracking";
    }
}
