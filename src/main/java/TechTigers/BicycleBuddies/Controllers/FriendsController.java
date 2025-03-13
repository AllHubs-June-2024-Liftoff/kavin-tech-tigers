package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("friends")
public class FriendsController {

    @Autowired
    private UserRepository userRepository; // For accessing user data

    @Autowired
    private FriendsService friendsService; // Service for friend-related logic

    // Display the friends index page
    @GetMapping("")
    public String index(Model model, @SessionAttribute(name = "user", required = false) User user) {
        // Retrieve a list of friends (or friend requests) for the logged-in user
        // Assuming that the logged-in user ID is passed or available (use a proper method for user retrieval)
        int loggedInUserId = user.getId(); // Replace this with actual logic for logged-in user
        //model.addAttribute("friendsList", friendsService.getAllFriends());
        //return "friends/index";
        // Fetch the logged-in user's friends and pass them to the view
        Optional<User> loggedInUser = userRepository.findById(loggedInUserId);
        if (loggedInUser.isPresent()) {
            model.addAttribute("friendsList", friendsService.getAllFriends(loggedInUserId));
        } else {
            model.addAttribute("friendsList", null);
        }
        return "friends/index";
    }

    // Add a friend (send friend request)
    @PostMapping("/add")
    public String addFriend(@SessionAttribute(name = "user", required = false) User user, @RequestParam int friendUserId, Model model) {
        boolean success = friendsService.addFriend(user.getId(), friendUserId);
        //model.addAttribute("message", "Friend request sent!");
        //return "redirect:/friends";
        //System.out.println("iam in line 48 in friendscontroller");
        if (success) {
            model.addAttribute("message", "Friend request sent!");
            //System.out.println("iam in line 51 in friendscontroller");
        } else {
            model.addAttribute("message", "Friend request failed! Maybe you are already friends.");
            System.out.println("iam in line 53 in friendscontroller");
        }
        return "redirect:/friends";
    }

    // Remove a friend
    @PostMapping("/remove")
    public String removeFriend(@RequestParam int userId, @RequestParam int friendUserId, Model model) {
        boolean success = friendsService.removeFriend(userId, friendUserId);
        // friendsService.removeFriend(userId, friendUserId);
        // model.addAttribute("message", "Friend removed!");
        // return "redirect:/friends";
        if (success) {
            model.addAttribute("message", "Friend removed!");
        } else {
            model.addAttribute("message", "Error! Friend could not be removed.");
        }
        return "redirect:/friends";
    }
}