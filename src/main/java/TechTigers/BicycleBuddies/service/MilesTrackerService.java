package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.MilesTrackerRepository;
import TechTigers.BicycleBuddies.models.Comment;
import TechTigers.BicycleBuddies.models.MilesTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//TODO: finish service layer
@Service
public class MilesTrackerService {
    private final MilesTrackerRepository milesTrackerRepository;

    @Autowired
    public MilesTrackerService(MilesTrackerRepository milesTrackerRepository) {
        this.milesTrackerRepository = milesTrackerRepository;
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



}
