package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.dto.RideInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideInviteRepository extends JpaRepository<RideInvite, Long> {
    // Additional custom queries can go here
}
