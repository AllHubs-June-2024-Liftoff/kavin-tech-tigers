package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProfileController {
// @Autowired
// private final ProfileService profileService;

    //TODO create a view for this
// @GetMapping("all-profiles")
//    public List<Profile> getAllProfiles(Model model) {
//    model.addAttribute("title", "List of all Profiles");
//    return profileService.getAllProfiles();
//}


    //Loads Mockup of profile View
    @GetMapping("/profile")
    public String profileView(Model model) {
        model.addAttribute("title", "User Profile");
        return "profile";
    }

//  TODO: Work titles with UserNames on error handling and Create CRUD Functionality for Comments
// Views Profile by ID
//    @GetMapping("/profile/{id}")
//    public Profile profileViewById(@PathVariable int id, Model model){
//    profileService.getProfileById(id);
//    model.addAttribute("profile", profile);
//    model.addAttribute("username", profile.getByUsername());
//    model.addAttribute("title", profile.getUserName()) +"'s profile");
//    return "profile";


    // LOADS Profile Edit View
    @GetMapping("/profile-edit") //add {id}
    public String profileEdit(Model model) {
        model.addAttribute("title", "Profile Edit");
        return "profile-edit";
    } //load mock edit page edit

    //CREATES Profile
// @PostMapping("/create")
// public Profile createProfile(@RequestBody @Valid Profile profile, Errors errors, Model model) {
//  if(errors.hasError()){
//  return "redirect:/create";
//  } else {
//  Profile savedProfile = profileService.saveProfile(profile);
//  model.addAttribute("username", profile.getByUsername());
//  model.addAttribute("title", profile.getUserName()) +"'s profile")
//  return "/profile/" + id;
//  }
//
//    }

// UPDATES Profiles

//@PutMapping("/profile-edit/{id}")
//public Profile profileUpdate(@PathVariable int id, @RequestBody @Valid Profile profile, Errors errors, Model model) {
// if(errors.hasErrors()){
// return "redirect:/profile-edit";
// } else {
 //model.addAttribute("username", profile.getByUsername());
// model.addAttribute("title", profile.getUserName()) +"'s profile")
//  return "redirect:/profile/" + id;
// }
//}
//

//DELETES Profiles
// @DeleteMapping("/profile/{id}")
// public Profile deleteProfile(@PathVariable int id, Model model){
// profileService.deleteProfile(id)
// return "Profile" + id + "has been deleted";
}