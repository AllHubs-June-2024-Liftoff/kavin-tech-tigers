package TechTigers.BicycleBuddies.models;

import java.util.ArrayList;

public class UserData {

    public static ArrayList<User> findByValue(String value, Iterable<User> allUsers) {
        ArrayList<User> results = new ArrayList<>();

        for (User user : allUsers) {
            String displayName = user.getDisplayName(); // Get the display name

            // Check if displayName is null before calling .toLowerCase()
            if (displayName != null && displayName.toLowerCase().contains(value.toLowerCase())) {
                results.add(user);
            }
        }

        return results;
    }


}
