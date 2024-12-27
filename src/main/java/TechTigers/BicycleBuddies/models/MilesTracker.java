package TechTigers.BicycleBuddies.models;

import java.util.Objects;

//@Entity
public class MilesTracker {
    //@GeneratedValue
    int id;
    // List<Rides> rides = new HashMap()<>;
    int numofRides;
    int milesEntered; //miles entered by user
    int milesMonthly;
    int milesTotal;

    public MilesTracker(){}

    public MilesTracker(int id, int numofRides, int milesEntered, int milesMonthly, int milesTotal) {
        this.id = id;
        this.numofRides = numofRides;
        this.milesEntered = milesEntered;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
    }

    public int getId() {
        return id;
    }

    public int getNumofRides() {return numofRides;}

    public void setNumofRides(int numofRides) {this.numofRides = numofRides;}

    public int getMilesEntered() {return milesEntered;}

    public void setMilesEntered(int milesEntered) {this.milesEntered = milesEntered;}

    public int getMilesMonthly() {return milesMonthly;}

    public void setMilesMonthly(int milesMonthly) {this.milesMonthly = milesMonthly;}

    public int getMilesTotal() {return milesTotal;}

    public void setMilesTotal(int milesTotal) {this.milesTotal = milesTotal;}

    //TODO: needs methods for mileage
    @Override
    public String toString() {
        return "MilesTracker{" +
                "id=" + id +
                ", numofRides=" + numofRides +
                ", milesEntered=" + milesEntered +
                ", milesMonthly=" + milesMonthly +
                ", milesTotal=" + milesTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilesTracker that = (MilesTracker) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}