package TechTigers.BicycleBuddies.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProfileController {
    @GetMapping
    public String profileView(){return "profile";}// load mock profile view

    @GetMapping()
    public String profileEdit() {
        return "profile-edit";
    } //load mock edit page edit


}