package TechTigers.BicycleBuddies.models;

import java.util.ArrayList;

public class UserData {

    public static ArrayList<User> findByUsername(String value, Iterable<User> allUsers){

        ArrayList<User> results = new ArrayList<>();

        for (User user : allUsers) {

            if(user.getUserName().toLowerCase().contains(value.toLowerCase())){
                results.add(user);
            }
        }

        return results;

    }

}
