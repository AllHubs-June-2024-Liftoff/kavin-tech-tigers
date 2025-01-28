package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO : NEEDS METHODS FOR MONTHLY TOTAL CALCULATION AND TOTAL MILES
@Entity
public class MilesTracker extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "milesTracker", cascade = CascadeType.ALL)
    private List<Entry> entries;

    private int milesMonthly = 0;
    private int milesTotal = 0;
    @Column(name= "month", nullable = false)
    private int MONTH = 30;



    public MilesTracker(){}

    public MilesTracker( User user, int milesMonthly, int milesTotal) {
        this.user= user;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
    }
    public MilesTracker(List<Entry> entries) {
        this.entries = entries;
    }

    //method I created for adding entries, monthly miles total & total sum of miles

    public void trackerSum(int miles,Entry entry){
       milesTotal += miles;
       entries.add(entry);
       if(entries.size() > 30){
         Entry oldest = entries.remove(0);
         milesMonthly-= oldest.getMiles();
       }
       milesMonthly += miles;
    }



    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", milesMonthly=" + milesMonthly +
                ", milesTotal=" + milesTotal +
                ", MONTH=" + MONTH +
                '}';
    }
}