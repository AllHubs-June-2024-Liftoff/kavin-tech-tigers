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
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("title", "All Comments");
        return "all-comments";
    }

@GetMapping("/add-comments/{rideId}")
public String showAddCommentForm(@PathVariable Long rideId, Model model){
        Optional<Ride> ride = Optional.ofNullable(rideService.getRideById(rideId));
        if(ride.isPresent()){
            model.addAttribute("ride", ride.get());
            model.addAttribute("title", "Add a Comment");
        } else{
            model.addAttribute("error", "Ride not found");
        }
        return "/add-comments";
}

@PostMapping("/add-comments/{rideId}/add")
    public String addComments(@RequestParam Long rideId, @RequestParam String content, Model model){
        Optional<Ride> ride = Optional.ofNullable(rideService.getRideById(rideId));
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTimestamp(LocalDateTime.now());
        comment.setLikes(0);
        comment.setRide(ride.orElse(null));
        commentService.saveComment(comment);
        return "redirect:/comments/all-comments";
}

}

