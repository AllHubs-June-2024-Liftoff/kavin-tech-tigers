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
//TODO: Update & Delete methods
    private CommentService commentService;
    private RideService rideService;

    @Autowired
    public CommentController(RideService rideService, CommentService commentService) {
        this.rideService= rideService;
        this.commentService= commentService;}



    @GetMapping("/all-comments")
    public String viewAllComments(Model model){
        Ride ride = rideService.getFirstRide();
        if(ride == null){
            model.addAttribute("error", "No rides found");
        }
        List<Comment> comments = commentService.getCommentsByRideId(ride.getId());
        model.addAttribute("ride", ride);
        model.addAttribute("comments", comments);
        model.addAttribute("title", "All Comments");
        return "comments/all-comments";
    }

    @GetMapping("/add-comments/{rideId}")
    public String showAddCommentForm(@PathVariable Long rideId, Model model){
        Optional<Ride> ride = Optional.ofNullable(rideService.getRideById(rideId));
        if(ride.isPresent()){
            model.addAttribute("ride", ride.get());
            model.addAttribute("comment", new Comment());
            model.addAttribute("title", "Add a Comment");
        } else{
            model.addAttribute("error", "Ride not found");
        }
        return "comments/add-comments";
    }

    @PostMapping("/add-comments/{rideId}/add")
    public String addComments(@PathVariable Long rideId, @ModelAttribute Comment comment, Model model){
        Optional<Ride> ride = Optional.ofNullable(rideService.getRideById(rideId));
        if(ride.isEmpty()){
            model.addAttribute("error", "Ride is not found");
            return "redirect:/comments/all-comments";
        }
        comment.setRide(ride.get());
        comment.setTimestamp(LocalDateTime.now());
        comment.setLikes(0);
        commentService.saveComment(comment);
        return "redirect:/comments/all-comments";
    }

    @PostMapping("/like/{id}")
    public String likeComment(@PathVariable int id, Model model) {
        Optional<Comment> optionalComment = commentService.getCommentById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.addLike();
            commentService.saveComment(comment);
        } else {
            model.addAttribute("error", "Comment not found.");
        }
        return "redirect:/comments/all-comments";
    }

    @GetMapping("/add-comments/{rideId}/add")
    public String redirectToAddCommentForm(@PathVariable Long rideId) {
        return "redirect:/comments/add-comments/" + rideId;
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteProfile(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/comments/all-comments";
    }
}

