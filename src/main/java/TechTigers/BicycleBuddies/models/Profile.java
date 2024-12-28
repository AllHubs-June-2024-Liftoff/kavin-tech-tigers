package TechTigers.BicycleBuddies.models;

import java.awt.*;

//@Entity
public class Profile extends User {
    //@NotBlank (message = "Name must not be blank.")
    // @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.")
    String displayName; // display name entered by user for profile
    String bio;
    Image bioPicture;  // not sure if this is the right datatype found in Java Documentation https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
    Comment comments;
    MilesTracker tracker;

    public Profile(String displayName, String bio, Image bioPicture, Comment comments, MilesTracker tracker) {
        this.displayName = displayName;
        this.bio = bio;
        this.bioPicture = bioPicture;
        this.comments = comments;
        this.tracker = tracker;
    }

    public String getDisplayName() {return displayName;}

    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}

    public Image getBioPicture() {return bioPicture;}

    public void setBioPicture(Image bioPicture) {this.bioPicture = bioPicture;}

    public Comment getComments() {return comments;}

    public void setComments(Comment comments) {this.comments = comments;}

    public MilesTracker getTracker() {return tracker;}

    public void setTracker(MilesTracker tracker) {this.tracker = tracker;}


}
