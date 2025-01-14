package TechTigers.BicycleBuddies.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String displayName;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User () {}

    public User(String username, String password, String displayName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public @NotNull String getDisplayName() {
        return displayName;
    }
}
