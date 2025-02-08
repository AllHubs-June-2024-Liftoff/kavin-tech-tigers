package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
