package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
//    //@Autowired
//     private ProfileService profileService;

    //Loads Mockup of profile View
    @GetMapping("/profile")
    public String profileView(Model model){
        model.addAttribute("title", "User Profile");
        return "profile";}

// Views Profile by ID
//    @GetMapping("/profile/{id}")
//    public Profile profileViewById(@PathVariable int id){
//    profileService.getProfileById(id).orElseThrow();
//    return "redirect:/";


// LOADS Profile Edit View
    @GetMapping("/profile-edit")
    public String profileEdit(Model model) {
        model.addAttribute("title", "Profile Edit");
        return "profile-edit";} //load mock edit page edit

// UPDATES Profiles
//@PutMapping("/profile/{id}")
//public Profile profileUpdate(@PathVariable int id, @RequestBody Profile profile) {
// profileService.updateProfile(id, profile);
// return "redirect:/profile-edit";

//DELETES Profiles
// @DeleteMapping("/profile/{id}")
// public Profile deleteProfile(@PathVariable int id){
// profileService.deleteProfile(id)
// return "Profile" + id + "has been deleted";
}