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


//    @GetMapping("/profile-edit{id}")
//    public String profileEdit(@PathVariable int id, Model model) {
//        Optional<User>profile= userService.getProfileById(id);
//        if(profile.isEmpty()){
//            model.addAttribute("error", "Profile with ID " +id +" doesn't exist.");
//            return "error";
//        }
//        model.addAttribute("title", "Edit Profile");
//        model.addAttribute("profile", profile.get());
//        return "profile-edit";
//    }
//
//@PostMapping("/create")
//    public String createProfile(@RequestBody @Valid User user, Errors errors, Model model){
//      if(errors.hasErrors()) {
//          model.addAttribute("error", "Validation error has occured.");
//          return "create-profile"; // might need to be changed to log-on page register
//      }
//      User savedProfile = userService.saveProfile(user);
//      return "redirect:/profile/" + savedProfile.getId();
// }
//
// @PutMapping("/profile-edit/{id}")
// public String profileUpdate(@PathVariable int id, @RequestBody @Valid User user, Errors errors, Model model) {
//        if(errors.hasErrors()){
//            model.addAttribute("error", "Validation error has occured.");
//            return "redirect:/profile-edit" + id;
//        }
//        User updatedProfile = userService.updateProfile(id, user);
//        model.addAttribute("username", updatedProfile.getUserName());
//        model.addAttribute("title", updatedProfile.getUserName());
//       return "redirect:/profile/" + updatedProfile.getId();
// }
//    @DeleteMapping("/profile/{id}")
//    public String deleteProfile(@PathVariable int id) {
//        userService.deleteProfile(id);
//        return "redirect:/index";
//    }
}



