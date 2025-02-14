package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.FriendshipRepository;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.Friendship;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    public void addFriend(int userId, int friendUserId) {
        User user = userRepository.findById(userId).orElse(null);
        User friend = userRepository.findById(friendUserId).orElse(null);

        if (user != null && friend != null) {
            Friendship existingFriendship = friendshipRepository.findByUserAndFriend(user, friend);
            if (existingFriendship == null) {
                friendshipRepository.save(new Friendship(user, friend, Friendship.FriendshipStatus.PENDING));
            }
        }
    }

    public void acceptFriendRequest(int userId, int friendUserId) {
        User user = userRepository.findById(userId).orElse(null);
        User friend = userRepository.findById(friendUserId).orElse(null);

        if (user != null && friend != null) {
            Friendship friendship = friendshipRepository.findByUserAndFriend(friend, user); // Find request
            if (friendship != null) {
                friendship.setStatus(Friendship.FriendshipStatus.ACCEPTED);
                friendshipRepository.save(friendship);
            }
        }
    }

    public List<Friendship> getAllFriends(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return friendshipRepository.findByUserAndStatus(user, Friendship.FriendshipStatus.ACCEPTED);
        }
        return List.of();
    }
}
