package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all profiles as List
    public List<User> getAllProfiles() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Get profile by ID
    public Optional<User> getProfileById(Long profileId) {
        return userRepository.findById(profileId);
    }

    // Save a new profile
    public User saveProfile(User user) {
        return userRepository.save(user);
    }

    // Update an existing profile
    public User updateProfile(Long profileId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(profileId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUserName(updatedUser.getUserName()); // Changed to setUserName
            user.setEmail(updatedUser.getEmail());       // Setting email
            // Add other properties here to update
            return userRepository.save(user);
        }
        return null; // Or throw an exception as needed
    }

    // Delete a profile
    public void deleteProfile(Long profileId) {
        userRepository.deleteById(profileId);
    }
}
