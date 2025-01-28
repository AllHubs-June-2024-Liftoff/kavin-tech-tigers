package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.EntryRepository;
import TechTigers.BicycleBuddies.data.MilesTrackerRepository;
import TechTigers.BicycleBuddies.data.RideRepository;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.Entry;
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
    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    @Autowired
    public MilesTrackerService(MilesTrackerRepository milesTrackerRepository, UserRepository userRepository, EntryRepository entryRepository) {
        this.milesTrackerRepository = milesTrackerRepository;
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
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


    public void deleteTracker(int id){
       milesTrackerRepository.deleteById(id);
    }


    public List<MilesTracker> getTrackerByUser(User user) {
        return milesTrackerRepository.findByUser(user);
    }
    public void addEntry(Entry entry) {
        MilesTracker milesTracker = entry.getMilesTracker();
        milesTracker.trackerSum(entry.getMiles(), entry);
        entryRepository.save(entry);
        saveMilesTracker(milesTracker);
    }

}
