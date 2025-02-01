package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MilesTracker extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "milesTracker", cascade = CascadeType.ALL)
    private List<Entry> entries = new ArrayList<>();
    private int milesMonthly = 0;
    private int milesTotal = 0;

    public MilesTracker(){}
    public MilesTracker(List<Entry> entries) {
        this.entries = entries;
    }
    public MilesTracker( User user, int milesMonthly, int milesTotal) {
        this.user= user;
        this.milesMonthly = milesMonthly;
        this.milesTotal = milesTotal;
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
    public void removeEntry(Entry entry){
        this.entries.remove(entry);
        this.milesTotal -=entry.getMiles();
        this.milesMonthly-= entry.getMiles();
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

    @Override
    public String toString() {
        return "MilesTracker{" +
                "user=" + user +
                ", entries=" + entries +
                ", milesMonthly=" + milesMonthly +
                ", milesTotal=" + milesTotal +
                '}';
    }
}