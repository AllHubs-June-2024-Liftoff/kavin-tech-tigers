package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ride extends AbstractEntity {

    @Column(nullable = false)
    private LocalDate date; // Date of the ride

    @Column(nullable = false)
    private double distance; // Distance covered during the ride in miles or kilometers

    @Column(nullable = false)
    private double duration; // Duration of the ride in hours

    @Column(length = 500)
    private String description; // Optional description of the ride

    private String route; // The route or location where the ride took place

    @Column(nullable = false)
    private String status = "scheduled"; // Default status (could be "scheduled", "completed", "canceled")

    @OneToMany(mappedBy= "ride")
    private List<Comment> comments = new ArrayList<>();


    // Default constructor (required by JPA)
    public Ride() {
    }

    // Constructor for convenience
    public Ride(LocalDate date, double distance, double duration, String description, String route) {
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.description = description;
        this.route = route;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Optional: Override toString for debugging or logging purposes
    @Override
    public String toString() {
        return "Ride{" +
                "id=" + this.getId() +
                ", date=" + date +
                ", distance=" + distance +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", route='" + route + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Method to cancel the ride (update status)
    public void cancelRide() {
        this.status = "canceled"; // Set status to canceled

    }
}
