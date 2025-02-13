package TechTigers.BicycleBuddies.data;

import TechTigers.BicycleBuddies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {  // ✅ Ensure it uses User

    Optional<User> findByUsername(String username);  // ✅ Fixed method name

    Optional<User> findByEmail(String email);
}
