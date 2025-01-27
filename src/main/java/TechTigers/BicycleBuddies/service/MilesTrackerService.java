package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.MilesTrackerRepository;
import TechTigers.BicycleBuddies.models.MilesTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
