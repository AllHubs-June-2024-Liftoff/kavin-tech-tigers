package TechTigers.BicycleBuddies.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.Entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    @Email
    private String email;

    private boolean isVerified;

    private int verificationCode = generateToken();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User () {}

    public User(String username, String password, String email, boolean isVerified) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.isVerified = isVerified;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    //Generates a number between 100000 and 999999
    public static int generateToken(){
        Random generator = new Random();
        return generator.nextInt(900000) + 100000;
    }

}
