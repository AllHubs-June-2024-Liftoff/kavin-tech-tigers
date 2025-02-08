package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Use Integer instead of Long

    private String rideName;
    private String rideOwner;
    private String location;
    private String date;
    private String status;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideOwner() {
        return rideOwner;
    }

    public void setRideOwner(String rideOwner) {
        this.rideOwner = rideOwner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
