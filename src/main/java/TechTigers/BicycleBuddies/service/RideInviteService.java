package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.RideInviteRepository;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.dto.RideInvite;  // Correct import for entity
import TechTigers.BicycleBuddies.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideInviteService {

    @Autowired
    private RideInviteRepository rideInviteRepository;

    @Autowired
    private UserRepository userRepository;

    // Updated method to use Long for senderId and receiverId
    public String sendRideInvite(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (sender == null || receiver == null) {
            return "User not found.";
        }

        RideInvite invite = new RideInvite();
        invite.setSender(sender);
        invite.setReceiver(receiver);
        rideInviteRepository.save(invite);

        return "Ride invite sent!";
    }

    // Updated method to use Long for inviteId
    public String acceptRideInvite(Long inviteId) {
        RideInvite invite = rideInviteRepository.findById(inviteId).orElse(null);
        if (invite != null) {
            invite.setAccepted(true);
            rideInviteRepository.save(invite);
            return "Ride invite accepted!";
        }
        return "Ride invite not found.";
    }
}
