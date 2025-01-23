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
public class ProfileController {
    @Autowired
    private UserService userService;
    private CommentService commentService;

    public ProfileController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("all-profiles")
    public String getAllProfiles(Model model) {
        List<User> profiles = userService.getAllProfiles();
        model.addAttribute("title", "List of all Profiles");
        model.addAttribute("profiles", profiles);
        return "all-profiles";
    }

    @GetMapping("/profile/{profileId}")
    public String profileViewById(@PathVariable int profileId, Model model) {
        Optional<User> optionalUser = userService.getProfileById(profileId);
        if (optionalUser.isPresent()) {
            User user = (User) optionalUser.get();
            List<Comment> comments = commentService.getCommentsByProfileId(profileId);
            model.addAttribute("comments",comments);
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("title", user.getDisplayName() +"'s profile");
            return "profile";
        } else {
        return "redirect:";
        }
    }

    @GetMapping("/profile-edit/{profileId}")
    public String profileEdit(@PathVariable int profileId, Model model) {
        Optional<User>optionalUser= userService.getProfileById(profileId);
        if(optionalUser.isPresent()){
            User user = (User) optionalUser.get();
            model.addAttribute("user", optionalUser.get());
            return "profile-edit";
        } else{
            return "redirect:";
        }
    }

 @PostMapping("/profile-edit/{profileId}/edit")
 public String profileUpdate(@PathVariable int profileId, @Valid User updatedUser, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("user", updatedUser);
            return "redirect:/profile-edit";
        }
        User updatedProfile = userService.updateProfile(profileId, updatedUser);
        model.addAttribute("userName", updatedUser.getUserName());
        model.addAttribute("title", updatedUser.getUserName() +"'s profile");
       return "redirect:/profile/" + profileId;
 }

 @GetMapping("/create-profile")
 public String displayCreateProfile(Model model){
        model.addAttribute("title","Create a new profile");
        model.addAttribute("user", new User());
        return "create-profile";
 }

@PostMapping("/create-profile")
    public String createProfile(@ModelAttribute @Valid User user, Errors errors, Model model){
      if(errors.hasErrors()) {
          model.addAttribute("error", "Validation error has occured.");
          return "create-profile";
      }
      User savedProfile = userService.saveProfile(user);
      return "redirect:/profile/" + savedProfile.getId();
 }

    @DeleteMapping("/profile-edit/{profileId}/delete")
    public String deleteProfile(@PathVariable int profileId) {
        userService.deleteProfile(profileId);
        return "redirect:/all-profiles";
    }
}



