package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
