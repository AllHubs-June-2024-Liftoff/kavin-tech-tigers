package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("friends")
public class FriendsController {

    @Autowired
    private UserRepository userRepository; // For accessing user data
    @Autowired
    private FriendsService friendsService; // Service for friend-related logic

    // Display the friends index page
    @GetMapping("")
    public String index(Model model) {
        // Retrieve a list of friends (or friend requests) for the logged-in user
        // Assuming that the logged-in user ID is passed or available (use a proper method for user retrieval)
        int loggedInUserId = 1; // Replace this with actual logic for logged-in user
        model.addAttribute("friendsList", friendsService.getAllFriends());
        return "friends/index";
    }

    // Add a friend (send friend request)
    @PostMapping("/add")
    public String addFriend(@RequestParam int userId, @RequestParam int friendUserId, Model model) {
        friendsService.addFriend(userId, friendUserId);
        model.addAttribute("message", "Friend request sent!");
        return "redirect:/friends";
    }

    // Remove a friend
    @PostMapping("/remove")
    public String removeFriend(@RequestParam int userId, @RequestParam int friendUserId, Model model) {
        friendsService.removeFriend(userId, friendUserId);
        model.addAttribute("message", "Friend removed!");
        return "redirect:/friends";
    }
}
