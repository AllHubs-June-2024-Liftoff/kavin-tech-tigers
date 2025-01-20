package TechTigers.BicycleBuddies.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class User extends AbstractEntity{
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private String location;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.")
    private String displayName; // display name entered by user for profile
    private String bio;
//    private Image bioPicture;  // not sure if this is the right datatype found in Java Documentation https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
//    private Comment comments;
//    private MilesTracker tracker;
//    private final List<User> friendList = new ArrayList<>();
    public User() {}

    public User(String userName, String fullName, String email, String password, String location, String displayName, String bio, Image bioPicture, Comment comments, MilesTracker tracker) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.displayName = displayName;
        this.bio = bio;
//        this.bioPicture = bioPicture;
//        this.comments = comments;
//        this.tracker = tracker;
    }

//    public List<User> getFriendList() {
//        return friendList;
//    }


    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public @NotBlank(message = "Name must not be blank.") @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.") String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NotBlank(message = "Name must not be blank.") @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.") String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

//    public Image getBioPicture() {
//        return bioPicture;
//    }

//    public void setBioPicture(Image bioPicture) {
//        this.bioPicture = bioPicture;
//    }

//    public Comment getComments() {
//        return comments;
//    }
//
//    public void setComments(Comment comments) {
//        this.comments = comments;
//    }
//
//    public MilesTracker getTracker() {
//        return tracker;
//    }
//
//    public void setTracker(MilesTracker tracker) {
//        this.tracker = tracker;
//    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", displayName='" + displayName + '\'' +
                ", bio='" + bio + '\'' +
//                ", bioPicture=" + bioPicture +
//                ", comments=" + comments +
//                ", tracker=" + tracker +
//                ", friendList=" + friendList +
                '}';
    }


}
