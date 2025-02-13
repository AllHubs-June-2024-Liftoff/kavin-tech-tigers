package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Users;
import TechTigers.BicycleBuddies.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendApiController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam int userId, @RequestParam int friendId) {
        return ResponseEntity.ok(friendService.addFriend(userId, friendId));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Users>> getFriends(@PathVariable int userId) {
        return ResponseEntity.ok(friendService.getFriends(userId));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFriend(@RequestParam int userId, @RequestParam int friendId) {
        return ResponseEntity.ok(friendService.removeFriend(userId, friendId));
    }
}
