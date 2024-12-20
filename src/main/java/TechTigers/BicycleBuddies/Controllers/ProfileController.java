package TechTigers.BicycleBuddies.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("profile-edit")
    public String profileEdit(){ return "profile-edit";}
}
