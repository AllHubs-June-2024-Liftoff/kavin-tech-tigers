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

    @NotNull
    private String password;

    public User () {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
