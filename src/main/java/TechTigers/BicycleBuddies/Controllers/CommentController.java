package TechTigers.BicycleBuddies.controller;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/add-comments/{profileId}/add")
    public String addComments(@RequestParam int profileId, @RequestParam String content, Model model) {
        Optional<User> user = userService.getProfileById(profileId);
        if (user.isPresent()) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setTimestamp(LocalDateTime.now());
            comment.setLikes(0);
            comment.setAuthor(user.get()); // Corrected method name
            commentService.saveComment(comment);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "redirect:/comments/all-comments";
    }
}
