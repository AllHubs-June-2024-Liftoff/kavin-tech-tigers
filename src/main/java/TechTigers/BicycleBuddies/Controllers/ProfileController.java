package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.MilesTracker;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.MilesTrackerService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MilesTrackerService milesTrackerService;

//TODO: improve profile by creating a session to pass the User information
    //once the user has logged on & being able to upload a photo of the bio
    @GetMapping("/all-profiles")
    public String getAllProfiles(Model model){
        List<User> profiles= userService.getAllProfiles();
        model.addAttribute("title", "List of all Profiles");
        model.addAttribute("profiles", profiles);
        return "profile/all-profiles";
    }

    @GetMapping("/profile/{profileId}")
    public String profileViewById(@PathVariable int profileId, Model model){
        User user = userService.getProfileById(profileId);
        MilesTracker milesTracker = milesTrackerService.getOrCreateTracker(user);
        model.addAttribute("user", user);
        model.addAttribute("milesTracker", milesTracker);
        model.addAttribute("userName", user.getDisplayName());
        model.addAttribute("title", user.getDisplayName() + "'s profile");
        return "profile/profile";
    }

    @GetMapping("/profile-edit/{profileId}")
    public String profileEdit(@PathVariable int profileId, Model model){
        User user = userService.getProfileById(profileId);
        model.addAttribute("user", user);
        return "profile/profile-edit";
    }

    @PostMapping("/profile-edit/{profileId}/edit")
    public String profileUpdate(@PathVariable int profileId,User updatedUser,Model model){
        User updatedProfile = userService.updateProfile(profileId, updatedUser);
        model.addAttribute("title", updatedUser.getUserName() + " 's profile");
        return "redirect:/profile/profile/" + profileId;
    }

    @GetMapping("/profile/create-profile")
    public String displayCreateProfile(Model model){
        model.addAttribute("title", "Create a new profile");
        model.addAttribute("user", new User());
        return "profile/create-profile";
    }

    @PostMapping("/create-profile")
    public String createProfile(@ModelAttribute User user, Model model){
        User savedProfile = userService.saveProfile(user);
        return "redirect:/profile/profile/" + savedProfile.getId();
    }

    @DeleteMapping("profile-edit/{profileId}/delete")
    public String deleteProfile(@PathVariable int profileId){
        userService.deleteProfile(profileId);
        return "redirect:/profile/all-profiles";
    }
}
