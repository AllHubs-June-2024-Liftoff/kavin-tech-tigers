package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // Constructor-based dependency injection
    public ProfileController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    // Fetch all profiles
    @GetMapping("/all-profiles")
    public String getAllProfiles(Model model) {
        List<User> profiles = userService.getAllProfiles();
        model.addAttribute("title", "List of all Profiles");
        model.addAttribute("profiles", profiles);
        return "profile/all-profiles";
    }

    // View profile by ID
    @GetMapping("/profile/{profileId}")
    public String profileViewById(@PathVariable Long profileId, Model model) {
        Optional<User> optionalUser = userService.getProfileById(profileId); // Using Long for profileId
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Comment> comments = commentService.getCommentsByProfileId(profileId);
            model.addAttribute("comments", comments);
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("title", user.getUserName() + "'s profile");
            return "profile/profile";
        } else {
            return "redirect:/profile/all-profiles";  // Redirect to all profiles if not found
        }
    }

    // Edit profile by ID
    @GetMapping("/profile-edit/{profileId}")
    public String profileEdit(@PathVariable Long profileId, Model model) {
        Optional<User> optionalUser = userService.getProfileById(profileId); // Using Long for profileId
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "profile/profile-edit";
        } else {
            return "redirect:/profile/all-profiles";  // Redirect to all profiles if not found
        }
    }

    // Update profile by ID
    @PostMapping("/profile-edit/{profileId}/edit")
    public String profileUpdate(@PathVariable Long profileId, @Valid User updatedUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", updatedUser);
            return "profile/profile-edit";
        }
        User updatedProfile = userService.updateProfile(profileId, updatedUser);
        model.addAttribute("userName", updatedUser.getUserName());
        model.addAttribute("title", updatedUser.getUserName() + "'s profile");
        return "redirect:/profile/profile/" + profileId;
    }

    // Create a new profile
    @GetMapping("/create-profile")
    public String displayCreateProfile(Model model) {
        model.addAttribute("title", "Create a new profile");
        model.addAttribute("user", new User());
        return "profile/create-profile";
    }

    @PostMapping("/create-profile")
    public String createProfile(@ModelAttribute @Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("error", "Validation error has occurred.");
            return "profile/create-profile";
        }
        User savedProfile = userService.saveProfile(user);
        return "redirect:/profile/profile/" + savedProfile.getId();
    }

    // Delete profile by ID
    @DeleteMapping("/profile-edit/{profileId}/delete")
    public String deleteProfile(@PathVariable Long profileId) {
        userService.deleteProfile(profileId);
        return "redirect:/profile/all-profiles";
    }
}
