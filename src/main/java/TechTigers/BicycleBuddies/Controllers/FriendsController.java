package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.Friendship;
import TechTigers.BicycleBuddies.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendsService friendsService;

    @GetMapping("")
    public String index(Model model) {
        int loggedInUserId = 1; // Replace with actual logic for logged-in user
        List<Friendship> friends = friendsService.getAllFriends(loggedInUserId);
        model.addAttribute("friendsList", friends);
        return "friends/index"; // Make sure the template is in the correct location
    }

    @PostMapping("/add")
    public String addFriend(@RequestParam int userId, @RequestParam int friendUserId) {
        friendsService.addFriend(userId, friendUserId);
        return "redirect:/friends";
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestParam int userId, @RequestParam int friendUserId) {
        friendsService.acceptFriendRequest(userId, friendUserId);
        return "redirect:/friends";
    }
}
