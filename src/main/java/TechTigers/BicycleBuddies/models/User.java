package TechTigers.BicycleBuddies.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity{

    @NotNull
    @Size(min = 2, max = 150, message = "Invalid userName.  Must be between 2 and 150 characters.")
    private String userName;
    @NotNull
    @Size(min = 2, max = 150, message = "Invalid fullName.  Must be between 2 and 150 characters.")
    private String fullName;
    @NotNull
    @Size(min = 2, max = 150, message = "Invalid email.  Must be between 2 and 150 characters.")
    private String email;
    @NotNull
    @Size(min = 2, max = 150, message = "Invalid password.  Must be between 2 and 150 characters.")
    private String password;
    @NotNull
    @Size(min = 2, max = 150, message = "Invalid location name.  Must be between 2 and 150 characters.")
    private String location;
//    private final List<User> friendList = new ArrayList<>();

    public User () {}

    public User(String userName, String fullName, String email, String password, String location) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.location = location;
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

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

//    public List<User> getFriendList() {
//        return friendList;
//    }

}
