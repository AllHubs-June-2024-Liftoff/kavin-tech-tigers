package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findByUserId(int userId);
    boolean existsByUserIdAndFriendId(int userId, int friendId);
}
