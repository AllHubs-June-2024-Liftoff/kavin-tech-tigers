package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getProfileById(int userId) {
        return userRepository.findById(userId); // Return Optional instead of directly returning the user
    }

    // Other methods
}
