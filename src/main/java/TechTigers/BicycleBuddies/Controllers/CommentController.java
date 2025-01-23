package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

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

//    @GetMapping("/profile/{profileId}")
//    public String displayCommentsForProfile(@PathVariable int profileId, Model model){
////        List<Comment> comments = commentService.getCommentsByProfileId(profileId);
//        model.addAttribute("comments", comments);
//        return "";
//    }
}

