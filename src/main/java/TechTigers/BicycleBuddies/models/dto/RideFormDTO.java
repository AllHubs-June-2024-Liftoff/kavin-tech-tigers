package TechTigers.BicycleBuddies.models.dto;

import TechTigers.BicycleBuddies.models.RideStatus;

import java.time.LocalDateTime;

public class RideFormDTO {

    private String userNameRideOwner;
    private LocalDateTime date;
    private double distance;
    private double duration;
    private String description;
    private String route;
    private RideStatus status;
    private double latitude;
    private double longitude;
    private Boolean scheduledEmail;

    public String getUserNameRideOwner() {
        return userNameRideOwner;
    }

    public void setUserNameRideOwner(String userNameRideOwner) {
        this.userNameRideOwner = userNameRideOwner;
    }

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

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getScheduledEmail() {
        return scheduledEmail;
    }

    public void setScheduledEmail(Boolean scheduledEmail) {
        this.scheduledEmail = scheduledEmail;
    }
}
