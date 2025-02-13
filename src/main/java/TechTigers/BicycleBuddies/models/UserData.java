package TechTigers.BicycleBuddies.models;

import java.util.ArrayList;

public class UserData {

    public static ArrayList<User> findByValue(String value, Iterable<User> allUsers){

        ArrayList<User> results = new ArrayList<>();

        for (User user : allUsers) {

            if(user.getDisplayName().toLowerCase().contains(value.toLowerCase())){
                results.add(user);
            }
        }

        return results;

    }

}