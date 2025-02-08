package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.Friend;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    // In-memory storage for users and friendships
    private List<User> users = new ArrayList<>();
    private List<Friend> friendships = new ArrayList<>();

    // Method to simulate adding a user
    public void addUser(User user) {
        users.add(user);
    }

    // Simulated method to get a user by ID
    public User getUserById(Long userId) {
        for (User user : users) {
            if (user.getId() == userId) {  // FIXED: Use '==' for primitive comparison
                return user;
            }
        }
        return null;
    }

    // In-memory method to add a friendship
    public Friend addFriend(Long userId, Long friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);

        if (user == null || friend == null) {
            throw new RuntimeException("User or Friend not found");
        }

        // Check if they are already friends
        for (Friend f : friendships) {
            if (f.getUser().equals(user) && f.getFriend().equals(friend)) {
                throw new RuntimeException("Users are already friends.");
            }
        }

        Friend friendship = new Friend(user, friend);
        friendships.add(friendship);  // Store friendship in memory
        return friendship;
    }

    // In-memory method to get a user's friends
    public List<Friend> getFriends(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Friend> userFriends = new ArrayList<>();
        for (Friend f : friendships) {
            if (f.getUser().equals(user)) {
                userFriends.add(f);
            }
        }
        return userFriends;
    }
}
