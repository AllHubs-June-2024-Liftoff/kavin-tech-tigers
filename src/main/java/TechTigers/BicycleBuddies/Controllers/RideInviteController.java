package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.service.RideInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride-invite")
public class RideInviteController {

    @Autowired
    private RideInviteService rideInviteService;

    // Updated to use Long instead of Integer
    @PostMapping("/send/{receiverId}")
    public String sendRideInvite(@RequestParam Long senderId, @PathVariable Long receiverId) {
        return rideInviteService.sendRideInvite(senderId, receiverId);
    }

    // Updated to use Long instead of Integer
    @PostMapping("/accept/{inviteId}")
    public String acceptRideInvite(@PathVariable Long inviteId) {
        return rideInviteService.acceptRideInvite(inviteId);
    }
}
