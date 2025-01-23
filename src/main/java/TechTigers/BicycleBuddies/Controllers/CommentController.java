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
@RequestMapping("comments")
public class CommentController {
//TODO: Update & Delete methods
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("all-comments")
    public String viewAllComments(Model model){
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "all-comments";
    }

@GetMapping("add-comments")
    public String addComments(@RequestParam int profileId, @RequestParam String content, Model model){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTimestamp(LocalDateTime.now());
        comment.setLikes(0);
        commentService.saveComment(comment);
        model.addAttribute("title", "Add a Comment");
        return "redirect:/profile/ + profileId";
}

}

