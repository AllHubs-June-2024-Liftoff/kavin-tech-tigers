package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    private UserRepository userRepository;

    // Method to get all friends for a user (dummy data for now)
    public List<User> getAllFriends() {
        // Convert Iterable<User> to List<User>
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList; // Now returns a List<User>
    }

    // Method to add a friend
    public boolean addFriend(int userId, int friendId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> friendOpt = userRepository.findById(friendId);

        if (userOpt.isPresent() && friendOpt.isPresent()) {
            User user = userOpt.get();
            User friend = friendOpt.get();

            // Implement the logic to add a friend
            // For example, add the friend to a list of friends or update a relationship table
            // user.addFriend(friend);  // Assuming you have a method like this in User class

            // Update the user and save
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // Method to remove a friend
    public boolean removeFriend(int userId, int friendId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> friendOpt = userRepository.findById(friendId);

        if (userOpt.isPresent() && friendOpt.isPresent()) {
            User user = userOpt.get();
            User friend = friendOpt.get();

            // Implement the logic to remove a friend
            // For example, remove the friend from a list of friends or update a relationship table
            // user.removeFriend(friend);  // Assuming you have a method like this in User class

            // Update the user and save
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // Other methods (e.g., for sending requests, accepting requests) can be added here...
}