package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Entry extends AbstractEntity{

    private LocalDateTime date;
    private String description;
    private int miles;

    @ManyToOne
    //TODO: Find out why Rides and Maps page works when this line of code is commented out
//    @JoinColumn(name="tracker_id")
    private MilesTracker milesTracker;

    public Entry(){}
//    public Entry(LocalDateTime date, String description, int miles, MilesTracker milesTracker) {
        public Entry(LocalDateTime date, String description, int miles) {
        this.date = date;
        this.description = description;
        this.miles = miles;
//        this.milesTracker = milesTracker;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public MilesTracker getMilesTracker() {
        return milesTracker;
    }

    public void setMilesTracker(MilesTracker milesTracker) {
        this.milesTracker = milesTracker;
    }
}
