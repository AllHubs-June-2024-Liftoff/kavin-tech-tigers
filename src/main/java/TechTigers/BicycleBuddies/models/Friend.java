package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Users friend;

    public Friend() {}

    public Friend(Users user, Users friend) {
        this.user = user;
        this.friend = friend;
    }

    public int getId() { return id; }
    public Users getUser() { return user; }
    public Users getFriend() { return friend; }

    public void setId(int id) { this.id = id; }
    public void setUser(Users user) { this.user = user; }
    public void setFriend(Users friend) { this.friend = friend; }
}
