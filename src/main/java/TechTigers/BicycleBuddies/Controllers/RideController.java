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
import TechTigers.BicycleBuddies.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private FriendsService friendsService;

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

    @PostMapping("/save")
    public String saveRide(@ModelAttribute Ride ride, @ModelAttribute RideFormDTO rideFormDTO, @RequestParam(name = "scheduledEmail", required = false) Boolean scheduled, @SessionAttribute(name = "user", required = false) User user) {

        ride.setUserNameRideOwner(user);
        rideService.saveRide(ride);


        if (Boolean.TRUE.equals(scheduled)) {
            RideUser rideUser = new RideUser();
            rideUser.setRide(ride);
            rideUser.setUser(user);
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
    public String deleteRide(@PathVariable int id) {
        rideService.deleteRide(id);  // Call service to delete the ride
        return "redirect:/rides";  // Redirect back to the list of rides after deletion
    }


    // View a single ride by ID
    @GetMapping("/{id}")
    public String getRideById(@PathVariable int id, Model model) {
        Ride ride = rideService.getRideById(id);  // Fetch ride by ID
        if (ride != null) {
            model.addAttribute("ride", ride);  // Add the ride to the model
            return "rideDetail";  // Render the ride details page (rideDetail.html)
        }
        return "redirect:/rides";  // If no ride found, redirect back to the list
    }

    // Cancel a ride by ID (new functionality)
    @PostMapping("/cancel/{id}")
    public String cancelRide(@PathVariable int id) {
        rideService.cancelRide(id);  // Call service to cancel the ride
        return "redirect:/rides";  // Redirect back to the list of rides after canceling
    }

    // Invite Friends to a Ride
    @GetMapping("/invite/{id}")
    public String showInviteFriendsPage(@PathVariable int id, Model model) {
        int loggedInUserId = 1;
        // Replace with actual logged-in user logic
        Ride ride = rideService.getRideById(id);
        if (ride != null) {
            model.addAttribute("rideId", ride.getId());
            model.addAttribute("friendsList", friendsService.getAllFriends(loggedInUserId));
            return "inviteFriends";
            // Refers to inviteFriends.html
        }
        return "redirect:/rides";
        // Redirect if ride is not found
    }

    //Handle Sending of Invites
    @PostMapping("/send-invites")
    public String sendInvites(@RequestParam int rideId, @RequestParam List<Integer> friendIds, RedirectAttributes redirectAttributes) {
        Ride ride = rideService.getRideById(rideId);
        if (ride != null) {
            for (int friendId : friendIds) {
                User friend = new User();
                friend.setId(friendId);
                RideUser rideUser = new RideUser();
                rideUser.setRide(ride);
                rideUser.setUser(friend);
                rideUserRepository.save(rideUser);// Save invitation to database
            }

        }
        // ✅ Set flash attribute for success message
        redirectAttributes.addFlashAttribute("successMessage", "Invitation sent successfully!");
        return "redirect:/rides"; // Redirect back to the rides page
    }
        }



