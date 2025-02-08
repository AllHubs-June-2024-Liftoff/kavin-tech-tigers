package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.FriendRequest;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> { // Changed Integer to Long
    // Custom query methods can be added here
}
