package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    // Corrected method to match the 'username' property in the User class
    User findByUsername(String username);

}
