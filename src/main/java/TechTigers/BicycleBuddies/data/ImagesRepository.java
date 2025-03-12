package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagesRepository extends CrudRepository<Images, Integer> {
    Optional<Images> findByName(String name);
}
