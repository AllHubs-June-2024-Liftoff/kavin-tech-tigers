package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.FriendRepository;
import TechTigers.BicycleBuddies.data.UsersRepository;
import TechTigers.BicycleBuddies.models.Friend;
import TechTigers.BicycleBuddies.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UsersRepository usersRepository;

    public String addFriend(int userId, int friendId) {
        Optional<Users> userOpt = usersRepository.findById(userId);
        Optional<Users> friendUserOpt = usersRepository.findById(friendId);

        if (userOpt.isEmpty() || friendUserOpt.isEmpty()) {
            return "One or both users not found.";
        }

        if (userId == friendId) {
            return "You cannot add yourself as a friend.";
        }

        if (friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
            return "You are already friends.";
        }

        Friend newFriend = new Friend(userOpt.get(), friendUserOpt.get());
        friendRepository.save(newFriend);
        return "Friend added successfully.";
    }

    public List<Users> getFriends(int userId) {
        List<Friend> friendships = friendRepository.findByUserId(userId);
        return friendships.stream()
                .map(Friend::getFriend)
                .collect(Collectors.toList());
    }

    public String removeFriend(int userId, int friendId) {
        List<Friend> friendships = friendRepository.findByUserId(userId);
        Optional<Friend> friendshipOpt = friendships.stream()
                .filter(f -> f.getFriend().getId() == friendId)
                .findFirst();

        if (friendshipOpt.isPresent()) {
            friendRepository.delete(friendshipOpt.get());
            return "Friend removed successfully.";
        }
        return "Friendship not found.";
    }
}
