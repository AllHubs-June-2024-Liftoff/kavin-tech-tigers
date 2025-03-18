package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.UserData;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("friends/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "friends/search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm,
                                       @ModelAttribute @Valid User user, Errors errors){
        if (errors.hasErrors()) {
            return "friends/search";
        }

        Iterable<User> users;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            users = userRepository.findAll();
            model.addAttribute("users", users);
        } else {
            users = UserData.findByValue(searchTerm, userRepository.findAll());
            model.addAttribute("users", users);
        }

        return "friends/search";
    }

}
