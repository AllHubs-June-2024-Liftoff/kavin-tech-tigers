package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.data.ConfigRepository;
import TechTigers.BicycleBuddies.data.RideUserRepository;
import TechTigers.BicycleBuddies.data.ScheduledEmailRepository;
import TechTigers.BicycleBuddies.data.UserRepository;
import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.RideUser;
import TechTigers.BicycleBuddies.models.ScheduledEmail;
import TechTigers.BicycleBuddies.models.User;
import TechTigers.BicycleBuddies.models.dto.RideFormDTO;
import TechTigers.BicycleBuddies.service.RideService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private RideUserRepository rideUserRepository;

    @Autowired
    private ScheduledEmailRepository scheduledEmailRepository;

    @Autowired
    private UserRepository userRepository;

    // View all rides
    @GetMapping
    public String getAllRides(Model model) {
        model.addAttribute("mapsApi", configRepository.findById(1).get());
        model.addAttribute("mapId", configRepository.findById(2).get());
        model.addAttribute("rides", rideService.getAllRides());  // Get all rides
        return "rides";  // Refers to rides.html
    }

    // Show form to create a new ride
    @GetMapping("/new")
    public String showNewRideForm(Model model) {
        model.addAttribute("mapsApi", configRepository.findById(1).get());
        model.addAttribute("mapId", configRepository.findById(2).get());
        model.addAttribute("rides", rideService.getAllRides());
        model.addAttribute("ride", new Ride());  // New ride object for the form
        return "rideForm";  // Refers to rideForm.html for adding a new ride
    }

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
    
    @PostMapping("/save")
    public String saveRide(@ModelAttribute Ride ride, @ModelAttribute RideFormDTO rideFormDTO, @RequestParam(name = "scheduledEmail", required = false) Boolean scheduled, HttpSession session) {

        User userId = getUserFromSession(session);

        rideService.saveRide(ride);


        if (Boolean.TRUE.equals(scheduled)) {
            RideUser rideUser = new RideUser();
            rideUser.setRide(ride);
            rideUser.setUser(userId);
            rideUserRepository.save(rideUser);

            ScheduledEmail scheduledEmail = new ScheduledEmail();
            scheduledEmail.setRide(ride);
            scheduledEmail.setEmailTime(rideFormDTO.getDate());
            scheduledEmailRepository.save(scheduledEmail);
        }

        return "redirect:/rides";
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
