package TechTigers.BicycleBuddies.models.dto;

import TechTigers.BicycleBuddies.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RideInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean accepted;
    private User sender;
    private User receiver;

    // Constructors
    public RideInvite() {}

    public RideInvite(Long id, User sender, User receiver, boolean accepted) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.accepted = accepted;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
