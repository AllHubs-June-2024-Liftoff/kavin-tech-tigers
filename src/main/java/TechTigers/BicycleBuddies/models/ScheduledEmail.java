package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class ScheduledEmail extends AbstractEntity {

    @Column(nullable = false)
    private LocalDateTime emailTime;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;

    public ScheduledEmail() {
    }

    public LocalDateTime getEmailTime() {
        return emailTime;
    }

    public void setEmailTime(LocalDateTime emailTime) {
        this.emailTime = emailTime;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "ScheduledEmail{" +
                "emailTime=" + emailTime +
                ", ride=" + ride +
                '}';
    }
}