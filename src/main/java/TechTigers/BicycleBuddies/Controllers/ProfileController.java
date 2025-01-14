package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String profileView(){return "profile";}// load mock profile view

    @GetMapping("/profile-edit")
    public String profileEdit() {
        return "profile-edit";
    } //load mock edit page edit

    @GetMapping("{username}")
    public String displayUserProfile(Model model, @PathVariable String username) {

        model.addAttribute("user", userRepository.findByUsername(username));
        return "profile";
    }


}