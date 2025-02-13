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
    public String viewAllTracking(Model model, @PathVariable int userId){
        User user = userService.getProfileById(userId);
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
    public String showAddTrackingForm(@PathVariable int userId, Model model){
        User user = userService.getProfileById(userId);
        model.addAttribute("user", user);
        model.addAttribute("entry", new Entry());
        model.addAttribute("title", "Add to Tracker");

        return "tracker/add-tracking";
    }

    @PostMapping("/add-tracking/{userId}/add")
    public String addTracking(@PathVariable int userId, @ModelAttribute Entry entry, Model model){
        User user = userService.getProfileById(userId);
        MilesTracker tracker = milesTrackerService.getOrCreateTracker(user);
        entry.setMilesTracker(tracker);
        milesTrackerService.addEntry(entry);
        entryRepository.save(entry);
        milesTrackerService.saveMilesTracker(tracker);
        return "redirect:/tracker/all-tracking/" + userId;
    }


    @DeleteMapping("/delete/{userId}/{entryId}")
    public String deleteEntry(@PathVariable int userId, @PathVariable int entryId) {
        User user = userService.getProfileById(userId);
        Entry entry = entryRepository.findById(entryId).orElseThrow(); //the orElseThrow was because intelliJ was angry
        MilesTracker milesTracker = milesTrackerService.getOrCreateTracker(user);
        milesTracker.removeEntry(entry);
        milesTrackerService.saveMilesTracker(milesTracker);
        entryRepository.deleteById(entryId);
        return "redirect:/tracker/all-tracking/" + userId;
    }
}