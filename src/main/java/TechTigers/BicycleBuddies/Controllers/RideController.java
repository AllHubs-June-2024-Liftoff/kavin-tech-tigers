package TechTigers.BicycleBuddies.Controllers;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class RideController {
    @Autowired
    private RideService rideService; // Service to fetch rides data

    // Mapping for the rides page
    @GetMapping("/rides")
    public String getAllRides(Model model) {
        List<Ride> rides = rideService.getAllRides(); // Fetching rides from the service
        model.addAttribute("rides", rides); // Adding the list of rides to the model
        return "rides"; // Returning the name of the HTML template (rides.html)
    }
}
