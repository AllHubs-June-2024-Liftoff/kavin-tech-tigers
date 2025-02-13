package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.security.SecureRandom;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "location")
    private String location;

    @Column(name = "email_verification_code", nullable = true)
    private Integer emailVerificationCode;  // ✅ Added

    @Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVerified;

    public User() {}

    public User(String username, String email, String password, String displayName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = (displayName != null && !displayName.trim().isEmpty()) ? displayName : username;
        this.isVerified = false;
    }

    // ✅ Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isVerified() { return isVerified; }
    public String getDisplayName() { return displayName; }
    public String getBio() { return bio; }
    public String getLocation() { return location; }
    public Integer getEmailVerificationCode() { return emailVerificationCode; }  // ✅ Added Getter

    // ✅ Setters
    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setVerified(boolean verified) { this.isVerified = verified; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public void setBio(String bio) { this.bio = bio; }
    public void setLocation(String location) { this.location = location; }
    public void setEmailVerificationCode(Integer emailVerificationCode) { this.emailVerificationCode = emailVerificationCode; } // ✅ Added Setter

    public boolean isMatchingPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // ✅ Static method to generate a verification token
    public static int generateToken() {
        SecureRandom random = new SecureRandom();
        return 100000 + random.nextInt(900000);
    }
}
