package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.MilesTrackerRepository;
import TechTigers.BicycleBuddies.data.RideRepository;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.MilesTracker;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: finish service layer
@Service
public class MilesTrackerService {
    MilesTracker milesTracker;
    private final MilesTrackerRepository milesTrackerRepository;
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    @Autowired
    public MilesTrackerService(MilesTrackerRepository milesTrackerRepository, RideRepository rideRepository, UserRepository userRepository) {
        this.milesTrackerRepository = milesTrackerRepository;
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }
    public List<MilesTracker> getAllTracking(){
        return (List<MilesTracker>) milesTrackerRepository.findAll();
    };
    public MilesTracker createTracker(MilesTracker milesTracker){
        return milesTrackerRepository.save(milesTracker);
    }

    public Optional<MilesTracker> getTrackerById(int id){
        return milesTrackerRepository.findById(id);
    }

    public MilesTracker saveMilesTracker(MilesTracker milesTracker){return milesTrackerRepository.save(milesTracker); }

    public MilesTracker updateTracker(int id, MilesTracker updatedTracker){
        MilesTracker existingTracker = milesTrackerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tracking with ID "+ id +" does not exist."));
        existingTracker.setNumofRides(updatedTracker.getNumofRides());
        existingTracker.setMilesEntered(updatedTracker.getMilesEntered());
        existingTracker.setMilesMonthly(updatedTracker.getMilesMonthly());
        existingTracker.setMilesTotal(updatedTracker.getMilesTotal());
        return milesTrackerRepository.save(existingTracker);
    }

    public void deleteTracker(int id){
       milesTrackerRepository.deleteById(id);
    }

    public int totalNumRides(){
       int numOfRides= milesTracker.setNumofRides(rideRepository.findAll().size());
        return numOfRides;
    }


}
