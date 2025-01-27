package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    // View all rides
    @GetMapping
    public String getAllRides(Model model) {
        model.addAttribute("rides", rideService.getAllRides());  // Get all rides
        return "rides";  // Refers to rides.html
    }

    // Show form to create a new ride
    @GetMapping("/new")
    public String showNewRideForm(Model model) {
        model.addAttribute("ride", new Ride());  // New ride object for the form
        return "rideForm";  // Refers to rideForm.html for adding a new ride
    }

    // Save a new ride
    @PostMapping("/save")
    public String saveRide(@ModelAttribute Ride ride) {
        rideService.saveRide(ride);  // Save ride via service
        return "redirect:/rides";  // Redirect back to the list of rides
    }

    // Delete a ride by ID
    @GetMapping("/delete/{id}")
    public String deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);  // Call service to delete the ride
        return "redirect:/rides";  // Redirect back to the list of rides after deletion
    }

    // View a single ride by ID
    @GetMapping("/{id}")
    public String getRideById(@PathVariable Long id, Model model) {
        Ride ride = rideService.getRideById(id);  // Fetch ride by ID
        if (ride != null) {
            model.addAttribute("ride", ride);  // Add the ride to the model
            return "rideDetail";  // Render the ride details page (rideDetail.html)
        }
        return "redirect:/rides";  // If no ride found, redirect back to the list
    }

    // Cancel a ride by ID (new functionality)
    @PostMapping("/cancel/{id}")
    public String cancelRide(@PathVariable Long id) {
        rideService.cancelRide(id);  // Call service to cancel the ride
        return "redirect:/rides";  // Redirect back to the list of rides after canceling
    }
}
