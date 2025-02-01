package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Entry;
import TechTigers.BicycleBuddies.models.MilesTracker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Integer> {
    List<Entry> findByMilesTracker(MilesTracker milesTracker);
}
