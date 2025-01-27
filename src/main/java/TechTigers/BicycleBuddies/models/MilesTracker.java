package TechTigers.BicycleBuddies.models;

import java.util.Objects;
//TODO: SERVICE LAYER
//TODO : NEEDS METHODS FOR MONTHLY TOTAL CALCULATION AND TOTAL MILES
//@Entity
public class MilesTracker extends AbstractEntity{

    // List<Rides> rides = new HashMap()<>;
    int numofRides;
    int milesEntered; //miles entered by user
    int milesMonthly;
    int milesTotal;


    public MilesTracker(){}

    public MilesTracker(int numofRides, int milesEntered, int milesMonthly, int milesTotal) {
        this.numofRides = numofRides;
        this.milesEntered = milesEntered;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
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
                ", numofRides=" + numofRides +
                ", milesEntered=" + milesEntered +
                ", milesMonthly=" + milesMonthly +
                ", milesTotal=" + milesTotal +
                '}';
    }

}