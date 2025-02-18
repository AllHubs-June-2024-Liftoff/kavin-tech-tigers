package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Friends;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends CrudRepository<Friends, Integer> {

    // Find all friends for a given user
    List<Friends> findByUser(User user);

    // Check if a friendship exists between two users
    Optional<Friends> findByUserAndFriend(User user, User friend);
    boolean existsByUserIdAndFriendId(int userId, int friendId);
    Optional<Friends> findByUserIdAndFriendId(int userId, int friendId);
}
