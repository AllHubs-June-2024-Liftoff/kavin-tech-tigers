package TechTigers.BicycleBuddies.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import javax.validation.constraints.NotNull;

import jakarta.persistence.OneToMany;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{

    @NotNull
    @NotBlank
    private String userName;

    private String fullName;

    @NotNull
    @Email
    private String email;

    private String pwHash;
    private String location;
    //Temporarily set to true for testing purposes
    private boolean isVerified;
    private final int verificationCode = generateToken();
    private int emailVerificationCode;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.")
    private String displayName; // display name entered by user for profile

    private String bio;
//    private Image bioPicture;  // not sure if this is the right datatype found in Java Documentation https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
    @OneToMany(mappedBy = "author", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User () {}

    public User(String userName, String fullName, String email, String password, String location, String displayName, String bio, Image bioPicture) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.location = location;
        this.displayName = displayName;
        this.bio = bio;
//        this.bioPicture = bioPicture;
    }



    public User(String userName, String password, String email, String displayName) {
        this.userName = userName;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.displayName = displayName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
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


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" +  + '\'' +
                ", location='" + location + '\'' +
                ", displayName='" + displayName + '\'' +
                ", bio='" + bio + '\'' +
//                ", bioPicture=" + bioPicture +
                '}';
    }

    //Commented out to remove validation for testing purposes
    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public int getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(int emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    //Generates a number between 100000 and 999999
    public static int generateToken(){
        Random generator = new Random();
        return generator.nextInt(900000) + 100000;
    }

}
