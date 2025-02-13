package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Friendship;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Integer> {
    Friendship findByUserAndFriend(User user, User friend);
    Friendship findByUserIdAndFriendId(int userId, int friendId);  // New method for checking if already friends
}
