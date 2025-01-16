package TechTigers.BicycleBuddies.Controllers;


import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    // @Autowired
    private UserService userService;
    //   TODO create a view for this
    @GetMapping("all-profiles")
    public String getAllProfiles(Model model) {
        List<User> profiles = userService.getAllProfiles();
        model.addAttribute("title", "List of all Profiles");
        model.addAttribute("profiles", profiles);
        return "all-profiles";
    }


    //Loads Mockup of profile View
    @GetMapping("/profile")
    public String profileView(Model model) {
        model.addAttribute("title", "User Profile");
        return "profile";
    }

    //  TODO: Create CRUD Functionality for Comments
//  TODO: Work on Error handling if profile is not found
    @GetMapping("/profile/{id}")
    public String profileViewById(@PathVariable int id, Model model) {
        Optional<User> profile = userService.getProfileById(id);
        if(profile.isEmpty()) {
            model.addAttribute("error", "Profile with ID" + id + "not found.");
            return "error"; // need to create error page
        }

        User user = profile.get();
        model.addAttribute("profile", user);
        model.addAttribute("username", user.getUserName());
        model.addAttribute("title", user.getUserName() + "'s Profile");
        return "profile";
    }

    @GetMapping("/profile-edit{id}")
    public String profileEdit(@PathVariable int id, Model model) {
        Optional<User>profile= userService.getProfileById(id);
        if(profile.isEmpty()){
            model.addAttribute("error", "Profile with ID " +id +" doesn't exist.");
            return "error";
        }
        model.addAttribute("title", "Edit Profile");
        model.addAttribute("profile", profile.get());
        return "profile-edit";
    }

@PostMapping("/create")
    public String createProfile(@RequestBody @Valid User user, Error errors, Model model){
      if(errors.hasErrors()) {

      }
 }
}



