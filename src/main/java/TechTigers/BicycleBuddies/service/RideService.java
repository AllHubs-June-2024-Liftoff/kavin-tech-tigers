package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.data.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Integer id) {
        Optional<Ride> ride = rideRepository.findById(id);
        return ride.orElse(null);
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public void deleteRide(Integer id) {
        rideRepository.deleteById(id);
    }

    public void cancelRide(Integer id) {
        Ride ride = getRideById(id);
        if (ride != null) {
            ride.setStatus("Canceled");
            rideRepository.save(ride);
        }
    }
}
