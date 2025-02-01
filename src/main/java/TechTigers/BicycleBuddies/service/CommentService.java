package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.CommentRepository;
import TechTigers.BicycleBuddies.data.RideRepository;
import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.Ride;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RideRepository rideRepository;


    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    public Comment getCommentById(int id) {

        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ride not found"));
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    public Comment updateComment(int id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with ID " + id + " does not exist."));
        existingComment.setAuthor(updatedComment.getAuthor());
        existingComment.setContent(updatedComment.getContent());
        existingComment.setTimestamp(updatedComment.getTimestamp());
        existingComment.setLikes(updatedComment.getLikes());
        return commentRepository.save(existingComment);
    }

    public List<Comment> getCommentsByRideId(int id) {
        Optional<Ride> ride = rideRepository.findById((id));
        return commentRepository.findByRide(ride.orElse(null));
    }

}
