package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Custom query method to find comments by User
    List<Comment> findByUser(User user);
}
