package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.UserData;
import TechTigers.BicycleBuddies.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("friends/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "friends/search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm){
        Iterable<User> users;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            users = userRepository.findAll();
        } else {
            users = UserData.findByValue(searchTerm, userRepository.findAll());
        }
        model.addAttribute("title", "Results");
        model.addAttribute("users", users);

        return "search";
    }

}
