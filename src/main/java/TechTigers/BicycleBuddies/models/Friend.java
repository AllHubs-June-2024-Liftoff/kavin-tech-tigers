package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Friend {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Foreign key to 'User'
    private User user;  // User who is the first part of the friendship

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id")  // Foreign key to 'User'
    private User friend;  // User who is the second part of the friendship

    // Default constructor (required by JPA)
    public Friend() {}

    // Constructor to initialize both users in the friendship
    public Friend(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
