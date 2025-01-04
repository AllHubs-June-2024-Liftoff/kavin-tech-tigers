package TechTigers.BicycleBuddies.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String profileView(){return "profile";}// load mock profile view

    @GetMapping("/profile-edit")
    public String profileEdit() {
        return "profile-edit";
    } //load mock edit page edit


}