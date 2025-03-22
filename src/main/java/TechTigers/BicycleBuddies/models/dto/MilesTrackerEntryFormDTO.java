package TechTigers.BicycleBuddies.models.dto;

import TechTigers.BicycleBuddies.models.MilesTracker;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class MilesTrackerEntryFormDTO {

    private LocalDateTime date;
    private String description;
    private int miles;

//    @ManyToOne
//    @JoinColumn(name="tracker_id")
//    private MilesTracker milesTracker;

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

//    public MilesTracker getMilesTracker() {
//        return milesTracker;
//    }
//
//    public void setMilesTracker(MilesTracker milesTracker) {
//        this.milesTracker = milesTracker;
//    }
}
