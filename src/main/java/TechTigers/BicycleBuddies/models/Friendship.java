package TechTigers.BicycleBuddies.models;
import TechTigers.BicycleBuddies.models.User;

import jakarta.persistence.*;

@Entity
@Table(name = "friendships")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // The new primary key column

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "status")
    private int status;

    public enum FriendshipStatus {
        PENDING, ACCEPTED, REJECTED
    }

    // Default constructor
    public Friendship() {}

    // Constructor with all properties
    public Friendship(User user, User friend, boolean isAccepted, int status) {
        this.user = user;
        this.friend = friend;
        this.isAccepted = isAccepted;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
