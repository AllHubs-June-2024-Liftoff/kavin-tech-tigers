package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.LinkedList;
import java.util.Queue;

//TODO : NEEDS METHODS FOR MONTHLY TOTAL CALCULATION AND TOTAL MILES
@Entity
public class MilesTracker extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    private Queue<Integer> dayTotal = new LinkedList<>();
    private int numofRides;
    private int milesEntered = 0;
    private int milesMonthly = 0;
    private int milesTotal = 0;
    private final int MONTH = 30;


    public MilesTracker(){}

    public MilesTracker(User user, Queue<Integer> dayTotal, int numofRides, int milesEntered, int milesMonthly, int milesTotal) {
        this.user= user;
        this.dayTotal = dayTotal;
        this.numofRides = numofRides;
        this.milesEntered = milesEntered;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
    }

    //method I created for adding monthly miles total & total sum of miles
    public void trackerSum(int num){
        if(dayTotal.size() == MONTH){
            milesMonthly -= dayTotal.poll();
        }
        dayTotal.add(num);
        milesMonthly += num;
        milesTotal += num;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Queue<Integer> getDayTotal() {
        return dayTotal;
    }

    public int getNumofRides() {
        return numofRides;
    }

    public int setNumofRides(int numofRides) {
        this.numofRides = numofRides;
        return numofRides;
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