package TechTigers.BicycleBuddies.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;

import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String userName;
    private String displayName;
    private String email;
    private String password;
    private String location;
    private String bio;
//    private Image profilePicture;
//    private final List<User> friendList = new ArrayList<>();

    public User () {}

    public User(String userName, String displayName, String email, String password, String location, String bio, Image profilePicture) {
        this.userName = userName;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.bio = bio;
//        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public String getDisplayName() { return displayName; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

//    public Image getProfilePicture() {
//        return profilePicture;
//    }

    public String getBio() {
        return bio;
    }

    //    public List<User> getFriendList() {
//        return friendList;
//    }

}
