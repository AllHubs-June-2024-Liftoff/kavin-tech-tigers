package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    // Get the logged-in user from session
    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        return userRepository.findById(userId).orElse(null);
    }

    // Set user in session after successful login
    public void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    public List<User> getAllProfiles() {
        return (List<User>) userRepository.findAll();
    }

    public User getProfileById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveProfile(User user) {
        return userRepository.save(user);
    }

    public void deleteProfile(int id) {
        userRepository.deleteById(id);
    }

    public User updateProfile(int id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile with ID " + id + " does not exist."));
        existingUser.setDisplayName(updatedUser.getDisplayName());
        existingUser.setLocation(updatedUser.getLocation());
        existingUser.setBio(updatedUser.getBio());
        return userRepository.save(existingUser);
    }
}


