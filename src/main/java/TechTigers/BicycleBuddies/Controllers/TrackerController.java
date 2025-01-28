package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.EntryRepository;
import TechTigers.BicycleBuddies.models.*;
import TechTigers.BicycleBuddies.service.MilesTrackerService;
import TechTigers.BicycleBuddies.service.RideService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("tracker")
public class TrackerController {

    private final MilesTrackerService milesTrackerService;
    private final UserService userService;
    private final EntryRepository entryRepository;


    @Autowired
    public TrackerController(MilesTrackerService milesTrackerService, UserService userService, EntryRepository entryRepository) {
        this.milesTrackerService = milesTrackerService;
        this.userService = userService;
        this.entryRepository = entryRepository;
    }



    @GetMapping("/all-tracking/{userId}")
    public String viewAllTracking(Model model, @PathVariable int userId){
        Optional<User> userOptional = userService.getProfileById(userId);
        if(userOptional.isEmpty()){
            model.addAttribute("error", "User not found");
            return "tracker/all-tracking";
        }
        User user= userOptional.get();
        MilesTracker milesTracker = milesTrackerService.getTrackerByUser(user).stream().findFirst().orElse(null);
       if(milesTracker == null){
           model.addAttribute("error", "Tracker not found for user");
           return "tracker/all-tracking";
       }
        List<Entry> entries = entryRepository.findByMilesTracker(milesTracker);

        model.addAttribute("milesTracker", milesTracker);
        model.addAttribute("user", user);
        model.addAttribute("entries", entries);
        model.addAttribute("title", "All Tracking");
        return "tracker/all-tracking";
    }

    @GetMapping("/add-tracking/{userId}")
    public String showAddTrackingForm(@PathVariable int userId, Model model){
        Optional<User> optionalUser = userService.getProfileById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            MilesTracker milesTracker = milesTrackerService.getTrackerByUser(user).stream().findFirst().orElse(null);
            if(milesTracker == null){
                milesTracker = new MilesTracker();
                milesTracker.setUser(user);
                milesTrackerService.saveMilesTracker(milesTracker);
            }
            model.addAttribute("user", user);
            model.addAttribute("entry", new Entry());
            model.addAttribute("title", "Add to Tracker");
        } else{
            model.addAttribute("error", "tracker not found");
        }
        return "tracker/add-tracking";
    }

    @PostMapping("/add-tracking/{userId}/add")
    public String addTracking(@PathVariable int userId, @ModelAttribute Entry entry, Model model){
        Optional<User> userOptional = userService.getProfileById(userId);
        if(userOptional.isEmpty()){
            model.addAttribute("error", "user is not found");
            return "redirect:/tracker/all-tracking" + userId;
        }
        User user= userOptional.get();
        MilesTracker milesTracker = milesTrackerService.getTrackerByUser(user).stream().findFirst().orElse(null);

        if(milesTracker == null){
            milesTracker = new MilesTracker();
            milesTracker.setUser(user);
            milesTracker = milesTrackerService.saveMilesTracker(milesTracker);
        }

        entry.setMilesTracker(milesTracker);
        milesTrackerService.addEntry(entry);
        entryRepository.save(entry);
        milesTrackerService.saveMilesTracker(milesTracker);
        return "redirect:/tracker/all-tracking/" + userId;
    }


    @DeleteMapping("/delete/{trackerId}")
    public String deleteTracker(@PathVariable int trackerId) {
        milesTrackerService.deleteTracker(trackerId);
        return "redirect:/tracker/all-tracking";
    }
}
