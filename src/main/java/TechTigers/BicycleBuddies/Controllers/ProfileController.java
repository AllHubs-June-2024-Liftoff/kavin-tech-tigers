package TechTigers.BicycleBuddies.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    //@Autowired
    // private ProfileRepository profileRepository

    @GetMapping("/profile")
    public String profileView(Model model){
        model.addAttribute("title", "User Profile");
        return "profile";}// load mock profile view

    @GetMapping("/profile-edit")
    public String profileEdit(Model model) {
        model.addAttribute("title", "Profile Edit");
        return "profile-edit";} //load mock edit page edit


}