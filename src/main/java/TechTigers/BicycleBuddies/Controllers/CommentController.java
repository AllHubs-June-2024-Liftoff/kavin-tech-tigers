package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {
//TODO: Update & Delete methods
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public CommentController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService= commentService;}



    @GetMapping("/all-comments")
    public String viewAllComments(Model model){
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("title", "All Comments");
        return "all-comments";
    }

@GetMapping("/add-comments/{profileId}")
public String showAddCommentForm(@PathVariable int profileId, Model model){
        Optional<User> user = userService.getProfileById(profileId);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            model.addAttribute("title", "Add a Comment");
        } else{
            model.addAttribute("error", "User not found");
        }
        return "/add-comments";
}

@PostMapping("/add-comments/{profileId}/add")
    public String addComments(@RequestParam int profileId, @RequestParam String content, Model model){
        Optional<User> user = userService.getProfileById(profileId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTimestamp(LocalDateTime.now());
        comment.setLikes(0);
        comment.setUser(user.orElse(null));
        commentService.saveComment(comment);
        return "redirect:/comments/all-comments";
}

}

