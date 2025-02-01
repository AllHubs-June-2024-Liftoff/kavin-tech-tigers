package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.service.CommentService;
import TechTigers.BicycleBuddies.service.RideService;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private RideService rideService;

    @GetMapping("/all-comments")
    public String viewAllComments(Model model){
        Ride ride = rideService.getFirstRide();
        List<Comment> comments = commentService.getCommentsByRideId(ride.getId());
        model.addAttribute("ride", ride);
        model.addAttribute("comments", comments);
        model.addAttribute("title", "All Comments");
        return "comments/all-comments";
    }

    @GetMapping("/add-comments/{rideId}")
    public String showAddCommentForm(@PathVariable Long rideId, Model model){
        Ride ride = rideService.getRideById(rideId);
        model.addAttribute("ride", ride);
        model.addAttribute("comment", new Comment());
        model.addAttribute("title", "Add a Comment");
        return "comments/add-comments";
    }

    @PostMapping("/add-comments/{rideId}/add")
    public String addComments(@PathVariable Long rideId, @ModelAttribute Comment comment, Model model){
        Ride ride = rideService.getRideById(rideId);
        comment.setRide(ride);
        comment.setTimestamp(LocalDateTime.now());
        comment.setLikes(0);
        commentService.saveComment(comment);
        return "redirect:/comments/all-comments";
    }

    @PostMapping("/like/{id}")
    public String likeComment(@PathVariable int id, Model model) {
        Comment comment = commentService.getCommentById(id);
        comment.addLike();
        commentService.saveComment(comment);
        return "redirect:/comments/all-comments";
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteProfile(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/comments/all-comments";
    }
}

