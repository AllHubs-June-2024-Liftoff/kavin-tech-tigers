package TechTigers.BicycleBuddies.service;


import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllProfiles(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getProfileById(int id){
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("Profile with ID " +id+ " doesn't exist.");
        }
        return userRepository.findById(id);
    }

    public User findUserByUsername(String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveProfile(User user) {return userRepository.save(user);}

    public void deleteProfile(int id) { userRepository.deleteById(id);}

    public User updateProfile(int id, User updatedUser){
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile with ID "+ id +" does not exist."));
        existingUser.setDisplayName(updatedUser.getDisplayName());
        existingUser.setLocation(updatedUser.getLocation());
        existingUser.setBio(updatedUser.getBio());
        return userRepository.save(existingUser);
    }
}



