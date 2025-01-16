package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.CommentRepository;
import TechTigers.BicycleBuddies.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository= commentRepository;
    }

    public List<Comment> getAllComments(){
        return (List<Comment>) commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(int id){
        if(!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment with ID " +id+ " doesn't exist.");
        }
        return commentRepository.findById(id);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment with ID "+ id + "doesn't exist.");
        }
        commentRepository.deleteById(id);
    }

    public Comment updateComment(int id, Comment updatedComment){
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Comment with ID "+ id +" does not exist."));
        existingComment.setAuthor(updatedComment.getAuthor());
        existingComment.setContent(updatedComment.getContent());
        existingComment.setTimestamp(updatedComment.getTimestamp());
        existingComment.setLikes(updatedComment.getLikes());
        return commentRepository.save(existingComment);
    }
}
