package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Friend;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser(User user);
}
