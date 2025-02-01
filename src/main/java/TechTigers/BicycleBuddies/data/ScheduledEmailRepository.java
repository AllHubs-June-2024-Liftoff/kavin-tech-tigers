package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.ScheduledEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduledEmailRepository extends CrudRepository<ScheduledEmail, Integer> {

    List<ScheduledEmail> findByEmailTimeBetween(final LocalDateTime startTime, final LocalDateTime endTime);

}
