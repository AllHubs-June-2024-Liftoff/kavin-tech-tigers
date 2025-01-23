package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Comment;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
//    List<Comment> findByProfileId(int id);
}
