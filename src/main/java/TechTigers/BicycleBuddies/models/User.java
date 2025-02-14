package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String passwordHash;

    private String displayName;  // Add display name
    private String location;     // Add location
    private String bio;          // Add bio

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friendship> friends;

    private boolean isVerified;

    // Change verificationCode to Integer
    private Integer verificationCode;

    private int emailVerificationCode;

    public User() {
        this.verificationCode = generateToken();  // Generate the token in the constructor
    }

    public User(String username, String passwordHash, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.isVerified = false;
        this.verificationCode = generateToken();  // Generate the token in the constructor
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(List<Friendship> friends) {
        this.friends = friends;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public int getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(int emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    // Add getters and setters for displayName, location, and bio
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Method to generate random 6-digit token
    public static int generateToken() {
        Random generator = new Random();
        return generator.nextInt(900000) + 100000;
    }

    // Method to check if entered password matches the stored hash
    public boolean isMatchingPassword(String password) {
        return this.passwordHash.equals(password); // You might want to use a password hash comparison here
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", location='" + location + '\'' +
                ", bio='" + bio + '\'' +
                ", isVerified=" + isVerified +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
