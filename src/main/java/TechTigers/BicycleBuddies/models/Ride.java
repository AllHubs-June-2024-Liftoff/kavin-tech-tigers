package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Ride extends AbstractEntity {

    private String userNameRideOwner;

    @Column(nullable = false)
    private LocalDateTime date; // Date of the ride

    @Column(nullable = false)
    private double distance; // Distance covered during the ride in miles or kilometers

    @Column(nullable = false)
    private double duration; // Duration of the ride in hours

    @Column(length = 500)
    private String description; // Optional description of the ride

    private String route; // The route or location where the ride took place

    @Column(nullable = false)
    private String status = "scheduled"; // Default status (could be "scheduled", "completed", "canceled")

    private double latitude;
    private double longitude;

    // Default constructor (required by JPA)
    public Ride() {
    }

    public Ride(String userNameRideOwner, LocalDateTime date, double distance, double duration, String description, String route, String status, double latitude, double longitude) {
        this.userNameRideOwner = userNameRideOwner;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.description = description;
        this.route = route;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUserNameRideOwner() {
        return userNameRideOwner;
    }

    public void setUserNameRideOwner(String userNameRideOwner) {
        this.userNameRideOwner = userNameRideOwner;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "userNameRideOwner='" + userNameRideOwner + '\'' +
                ", date=" + date +
                ", distance=" + distance +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", route='" + route + '\'' +
                ", status='" + status + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    // Method to cancel the ride (update status)
    public void cancelRide() {
        this.status = "canceled"; // Set status to canceled

    }
}
