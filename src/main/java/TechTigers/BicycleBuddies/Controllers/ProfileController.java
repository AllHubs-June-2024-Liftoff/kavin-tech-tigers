package TechTigers.BicycleBuddies.Controllers;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    // @Autowired
    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    //    //   TODO create a view for this
//    @GetMapping("all-profiles")
//    public String getAllProfiles(Model model) {
//        List<User> profiles = userService.getAllProfiles();
//        model.addAttribute("title", "List of all Profiles");
//        model.addAttribute("profiles", profiles);
//        return "all-profiles";
//    }


    //Loads Mockup of profile View
//    @GetMapping("/profile")
//    public String profileView(Model model) {
//        model.addAttribute("title", "User Profile");
//        return "profile";
//    }

    //  TODO: Create CRUD Functionality for Comments
//  TODO: Work on Error handling if profile is not found
    @GetMapping("/profile/{profileId}")
    public String profileViewById(@PathVariable int profileId, Model model) {
        Optional<User> optionalUser = userService.getProfileById(profileId);
        if (optionalUser.isPresent()) {
            User user = (User) optionalUser.get();
            model.addAttribute("user", user);
            model.addAttribute("username", user.getUserName());
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
 @PostMapping("/profile-edit/{profileId}")
 public String profileUpdate(@PathVariable int profileId, @Valid User updatedUser, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("user", updatedUser);
            return "redirect:/profile-edit";
        }
        User updatedProfile = userService.updateProfile(profileId, updatedUser);
        model.addAttribute("username", updatedUser.getUserName());
        model.addAttribute("title", updatedUser.getUserName() +"'s profile");
       return "redirect:/profile/" + profileId;
 }
//@PostMapping("/create")
//    public String createProfile(@RequestBody @Valid User user, Errors errors, Model model){
//      if(errors.hasErrors()) {
//          model.addAttribute("error", "Validation error has occured.");
//          return "create-profile"; // might need to be changed to log-on page register
//      }
//      User savedProfile = userService.saveProfile(user);
//      return "redirect:/profile/" + savedProfile.getId();
// }
//    @DeleteMapping("/profile-edit/{profileId}")
//    public String deleteProfile(@PathVariable int profileId) {
//        userService.deleteProfile(profileId);
//        return "redirect:/index";
//    }
}



