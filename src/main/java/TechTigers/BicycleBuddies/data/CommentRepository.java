package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByRide(Ride ride);
    Optional<Comment> findById(Integer id);
}
