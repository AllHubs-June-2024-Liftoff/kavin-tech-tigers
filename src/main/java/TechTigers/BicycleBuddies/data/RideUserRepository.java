package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.RideUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideUserRepository extends CrudRepository<RideUser, Integer> {

    List<RideUser> findByRide(final Ride ride);

}