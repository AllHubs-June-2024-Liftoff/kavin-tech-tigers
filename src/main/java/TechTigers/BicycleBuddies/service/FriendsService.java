package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.*;
import TechTigers.BicycleBuddies.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendsRepository friendsRepository;


    // Method to get all friends for a user (dummy data for now)
    //public List<User> getAllFriends() {
        // Convert Iterable<User> to List<User>
       // Iterable<User> users = userRepository.findAll();
       // List<User> userList = new ArrayList<>();
        //users.forEach(userList::add);
        //return userList; // Now returns a List<User>
   // }
    public List<User> getAllFriends(int userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Fetch only the friends of the given user
            List<Friends> friendships = friendsRepository.findByUser(user);

            List<User> friendsList = new ArrayList<>();
            for (Friends friendship : friendships) {
                friendsList.add(friendship.getFriend()); // Extracting friends
            }

            return friendsList;
        }

        return new ArrayList<>(); // Return an empty list if the user is not found
    }

    // Method to add a friend
    public boolean addFriend(int userId, int friendId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> friendOpt = userRepository.findById(friendId);
        System.out.println("iam in line 54 in friendsservice");
        if (userOpt.isPresent() && friendOpt.isPresent()) {
            User user = userOpt.get();
            User friend = friendOpt.get();

            // Implement the logic to add a friend
            // For example, add the friend to a list of friends or update a relationship table
            // user.addFriend(friend);  // Assuming you have a method like this in User class
// Check if friendship already exists
            if (friendsRepository.existsByUserIdAndFriendId(userId, friendId)) {
                System.out.println("iam in line 64 in friendsservice");
                return false; // Friend request already exists
            }
            // Implement the logic to add a friend
            Friends friendship = new Friends();
            friendship.setUser(user);
            friendship.setFriend(friend);
            friendship.setStatus("PENDING"); // Initial status as PENDING

            // Save the friendship in the repository
            friendsRepository.save(friendship);

            // Update the user and save
            userRepository.save(user);
            System.out.println("iam in line 78 in friendsservice");
            return true;
        } else {
            System.out.println("iam in line 81 in friendsservice");
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
            // user.removeFriend(friend);  // Assuming you have a method like this in User class
            Optional<Friends> friendshipOpt = friendsRepository.findByUserIdAndFriendId(userId, friendId);
            if (friendshipOpt.isPresent()) {
                friendsRepository.delete(friendshipOpt.get());
                // Update the user and save
                userRepository.save(user);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }



    // Other methods (e.g., for sending requests, accepting requests) can be added here...
}
