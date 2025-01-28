package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

//TODO: SERVICE LAYER
//TODO : NEEDS METHODS FOR MONTHLY TOTAL CALCULATION AND TOTAL MILES
@Entity
public class MilesTracker extends AbstractEntity{
    private Queue<Integer> dayTotal = new LinkedList<>();
    private int numofRides = 0;
    private int milesEntered = 0;
    private int milesMonthly = 0;
    private int milesTotal = 0;
    private final int MONTH = 30;


    public MilesTracker(){}

    public MilesTracker(Queue<Integer> dayTotal, int numofRides, int milesEntered, int milesMonthly, int milesTotal) {
        this.dayTotal = dayTotal;
        this.numofRides = numofRides;
        this.milesEntered = milesEntered;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
    }

    public Queue<Integer> getDayTotal() {
        return dayTotal;
    }

    public int getNumofRides() {
        return numofRides;
    }

    public void setNumofRides(int numofRides) {
        this.numofRides = numofRides;
    }

    public int getMilesEntered() {
        return milesEntered;
    }

    public void setMilesEntered(int milesEntered) {
        this.milesEntered = milesEntered;
    }

    public int getMilesMonthly() {
        return milesMonthly;
    }

    public void setMilesMonthly(int milesMonthly) {
        this.milesMonthly = milesMonthly;
    }

    public int getMilesTotal() {
        return milesTotal;
    }

    public void setMilesTotal(int milesTotal) {
        this.milesTotal = milesTotal;
    }

    public int getMONTH() {
        return MONTH;
    }

    @Override
    public String toString() {
        return "MilesTracker{" +
                "dayTotal=" + dayTotal +
                ", numofRides=" + numofRides +
                ", milesEntered=" + milesEntered +
                ", milesMonthly=" + milesMonthly +
                ", milesTotal=" + milesTotal +
                ", MONTH=" + MONTH +
                '}';
    }
}