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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: finish service layer
@Service
public class MilesTrackerService {
    private User user;
    @Autowired
    private MilesTrackerRepository milesTrackerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntryRepository entryRepository;
    private Entry entries;


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

    //this method checks if user has a milestracker if not it will create one for them
    public MilesTracker getOrCreateTracker(User user){
        List<MilesTracker> tracker = milesTrackerRepository.findByUser(user);
        if(!tracker.isEmpty()){
            return tracker.get(0);
        } else {
            MilesTracker newTracker;
            newTracker = new MilesTracker();
            newTracker.setUser(user);
            newTracker.setEntries(new ArrayList<>());
            return milesTrackerRepository.save(newTracker);
        }
    }

    public void addEntry(Entry entry) {
        MilesTracker milesTracker = entry.getMilesTracker();
        milesTracker.trackerSum(entry.getMiles(), entry);
        entryRepository.save(entry);
        saveMilesTracker(milesTracker);
    }

}