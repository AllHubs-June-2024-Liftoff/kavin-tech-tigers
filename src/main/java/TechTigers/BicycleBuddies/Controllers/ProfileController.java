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
//   TODO create a view for this
//   @GetMapping("all-profiles")
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

//  TODO: Create CRUD Functionality for Comments
//  TODO: Work on Error handling if profile is not found
// Views Profile by ID
//    @GetMapping("/profile/{id}")
//    public Profile profileViewById(@PathVariable int id, Model model){
//    Profile profile = profileService.getProfileById(id);
//    model.addAttribute("profile", profile);
//    model.addAttribute("username", profile.getUsername());
//    model.addAttribute("title", profile.getUserName() +"'s profile");
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
//  if(errors.hasErrors()){
//  return "redirect:/create";
//  } else {
//  Profile savedProfile = profileService.saveProfile(profile);
//  model.addAttribute("username", savedProfile.getUsername());
//  model.addAttribute("title", savedProfile.getUserName() +"'s profile");
//  return "redirect:/profile/" + savedProfile.getId();
//  }
//
//    }

// UPDATES Profiles

//@PutMapping("/profile-edit/{id}")
//public Profile profileUpdate(@PathVariable int id, @RequestBody @Valid Profile profile, Errors errors, Model model) {
// if(errors.hasErrors()){
// return "redirect:/profile-edit";
// } else {
//  Profile updatedProfile = profileService.updateProfile(id, profile);
 //model.addAttribute("username", updatedProfile.getUsername());
// model.addAttribute("title", updatedProfile.getUserName()) +"'s profile");
//  return "redirect:/profile/" + updatedProfile.getById();
// }
//}
//

//DELETES Profiles
// @DeleteMapping("/profile/{id}")
// public Profile deleteProfile(@PathVariable int id, Model model){
// profileService.deleteProfile(id);
// return "redirect/";
}