package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.MilesTracker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilesTrackerRepository extends CrudRepository<MilesTracker, Integer> {
}
