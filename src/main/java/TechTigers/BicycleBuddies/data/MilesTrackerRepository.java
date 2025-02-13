package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.MilesTracker;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilesTrackerRepository extends CrudRepository<MilesTracker, Integer> {
    List<MilesTracker> findByUser(User user);;
}