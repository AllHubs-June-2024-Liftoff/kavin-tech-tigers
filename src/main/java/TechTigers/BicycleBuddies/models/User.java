package TechTigers.BicycleBuddies.models;

import java.util.ArrayList;
import java.util.List;
//import jakarta.persistence.Entity;

//@Entity
public class User extends AbstractEntity{

    private String userName;
    private String fullName;
    private String email;
    private String password;
    private String location;
    private final List<User> friendList = new ArrayList<>();

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

    public List<User> getFriendList() {
        return friendList;
    }

}
