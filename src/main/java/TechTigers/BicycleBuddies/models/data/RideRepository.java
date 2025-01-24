package TechTigers.BicycleBuddies.models.data;

import TechTigers.BicycleBuddies.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    // JpaRepository already provides basic CRUD operations, so no need for additional methods.
}
