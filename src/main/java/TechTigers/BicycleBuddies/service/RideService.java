package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.data.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElse(null);
    }

    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }

    // New method to cancel a ride
    public void cancelRide(Long id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            ride.setStatus("canceled"); // Mark as "canceled"
            rideRepository.save(ride); // Save the updated ride
        }
    }
}
