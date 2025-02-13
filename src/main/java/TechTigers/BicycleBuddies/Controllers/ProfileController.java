package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    private static final String USER_SESSION_KEY = "user";

    private User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(USER_SESSION_KEY);
        return (userId != null) ? userService.getProfileById(userId) : null;
    }

    @GetMapping("")
    public String displayProfile(HttpSession session, Model model) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "redirect:/login"; // Redirect if no user is logged in
        }

        // ✅ Use the correct method names from `User.java`
        model.addAttribute("username", user.getUsername());  // ✅ FIXED
        model.addAttribute("displayName", user.getDisplayName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("bio", user.getBio());
        model.addAttribute("location", user.getLocation());

        return "profile";  // ✅ Ensure you have profile.html
    }

    @GetMapping("/all-profiles")  // ✅ Ensure this matches the URL in your template
    public String getAllProfiles(Model model) {
        List<User> profiles = userService.getAllProfiles();
        model.addAttribute("profiles", profiles);
        model.addAttribute("title", "All Profiles");
        return "profile/all-profiles";  // ✅ This must match the actual Thymeleaf template name
    }
}
