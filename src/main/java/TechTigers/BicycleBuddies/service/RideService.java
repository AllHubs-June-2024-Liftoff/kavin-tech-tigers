package TechTigers.BicycleBuddies.service;


import TechTigers.BicycleBuddies.data.*;
import TechTigers.BicycleBuddies.models.*;
import TechTigers.BicycleBuddies.models.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private RideUserRepository rideUserRepository;


    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public Ride getRideById(int id) {
        return rideRepository.findById(id).orElse(null);
    }

    public void deleteRide(int id) {
        rideRepository.deleteById(id);
    }

    // New method to cancel a ride
    public void cancelRide(int id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.canceled);
            rideRepository.save(ride); // Save the updated ride
        }
    }


    public Ride getFirstRide() {
        return rideRepository.findAll().stream().findFirst().orElse(null);
    }

    public void inviteFriendsToRide(int rideId, List<Integer> friendIds) {
        Ride ride = getRideById(rideId);
        if (ride != null) {
            for (int friendId : friendIds) {
                User friend = new User();
                friend.setId(friendId);

                RideUser rideUser = new RideUser();
                rideUser.setRide(ride);
                rideUser.setUser(friend);
                rideUserRepository.save(rideUser); // Save invitation
            }
        }
    }
}
