package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Ride;
import org.springframework.data.repository.CrudRepository;

public interface RideInfoRepository extends CrudRepository <Ride, Integer> {
}