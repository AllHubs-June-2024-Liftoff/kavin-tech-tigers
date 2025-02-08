package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

    @Id
    private Long id;  // Add the ID field
    private String userName;
    private String fullName;
    private String email;
    private String pwHash;
    private String location;
    private boolean isVerified;
    private String displayName;
    private String bio;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Constructor for AuthenticationController (simplified version)
    public User(String userName, String email, String password, String displayName) {
        this.userName = userName;
        this.email = email;
        this.pwHash = encoder.encode(password);  // Hash the password
        this.displayName = displayName;
        this.isVerified = false;  // Set a default value for verification status
        // Default empty values for other fields (if necessary)
        this.fullName = "";
        this.location = "";
        this.bio = "";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Password matching method
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    // Method to generate and validate verification token
    public static int generateToken() {
        return (int) (Math.random() * 900000) + 100000;
    }
}
