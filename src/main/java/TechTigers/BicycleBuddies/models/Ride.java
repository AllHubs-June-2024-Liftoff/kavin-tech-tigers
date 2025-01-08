package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // Marks this class as a JPA Entity
//@Table(name = "rides") // Maps the class to a table named "rides" in the database
public class Ride extends AbstractEntity {

    @Column(nullable = false) // Makes this column non-nullable
    private LocalDate date; // Date of the ride

    @Column(nullable = false) // Makes this column non-nullable
    private double distance; // Distance covered during the ride in miles or kilometers

    @Column(nullable = false) // Makes this column non-nullable
    private double duration; // Duration of the ride in hours

    @Column(length = 500) // Sets the maximum length of the description
    private String description; // Optional description of the ride

    private String route; // The route or location where the ride took place

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

    // Getters and Setters (required for JPA and to access data)


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
                '}';
    }
}
