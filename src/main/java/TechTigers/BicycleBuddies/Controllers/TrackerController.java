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
    @Autowired
    private MilesTrackerService milesTrackerService;
    @Autowired
    private UserService userService;
    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/all-tracking/{userId}")
    public String viewAllTracking(Model model, @PathVariable Long userId){
        Optional<User> userOptional = userService.getProfileById(userId);  // Returns Optional<User>
        if (userOptional.isEmpty()) {
            // Handle the case where the user is not found
            model.addAttribute("error", "User not found");
            return "error";  // Or redirect to a different page, e.g., "error"
        }

        User user = userOptional.get();  // Get the User object from the Optional
        MilesTracker milesTracker = milesTrackerService.getOrCreateTracker(user);
        List<Entry> entries = milesTracker.getEntries();
        model.addAttribute("userId", userId);
        model.addAttribute("milesTracker", milesTracker);
        model.addAttribute("user", user);
        model.addAttribute("entries", entries);
        model.addAttribute("title", "All Tracking");
        return "tracker/all-tracking";
    }

    @GetMapping("/add-tracking/{userId}")
    public String showAddTrackingForm(@PathVariable Long userId, Model model){
        Optional<User> userOptional = userService.getProfileById(userId);
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "error";
        }

        User user = userOptional.get();  // Get the User object from the Optional
        model.addAttribute("user", user);
        model.addAttribute("entry", new Entry());
        model.addAttribute("title", "Add to Tracker");

        return "tracker/add-tracking";
    }

    @PostMapping("/add-tracking/{userId}/add")
    public String addTracking(@PathVariable Long userId, @ModelAttribute Entry entry, Model model){
        Optional<User> userOptional = userService.getProfileById(userId);
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "error";
        }

        User user = userOptional.get();  // Get the User object from the Optional
        MilesTracker tracker = milesTrackerService.getOrCreateTracker(user);
        entry.setMilesTracker(tracker);
        milesTrackerService.addEntry(entry);
        entryRepository.save(entry);
        milesTrackerService.saveMilesTracker(tracker);
        return "redirect:/tracker/all-tracking/" + userId;
    }

    @DeleteMapping("/delete/{userId}/{entryId}")
    public String deleteEntry(@PathVariable Long userId, @PathVariable int entryId) {
        Optional<User> userOptional = userService.getProfileById(userId);
        if (userOptional.isEmpty()) {
            return "error";  // Or handle the error appropriately
        }

        User user = userOptional.get();  // Get the User object from the Optional
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found")); // Fixed the error here
        MilesTracker milesTracker = milesTrackerService.getOrCreateTracker(user);
        milesTracker.removeEntry(entry);
        milesTrackerService.saveMilesTracker(milesTracker);
        entryRepository.deleteById(entryId);
        return "redirect:/tracker/all-tracking/" + userId;
    }
}
