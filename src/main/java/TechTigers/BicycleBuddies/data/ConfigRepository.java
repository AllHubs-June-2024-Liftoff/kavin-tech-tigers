package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<Config, Integer> {
}
