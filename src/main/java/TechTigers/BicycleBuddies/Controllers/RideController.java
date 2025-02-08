package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public String getAllRides(Model model) {
        List<Ride> rides = rideService.getAllRides();
        model.addAttribute("rides", rides);
        return "rideList";
    }

    @GetMapping("/{id}")
    public String getRideById(@PathVariable Integer id, Model model) {
        Ride ride = rideService.getRideById(id);
        if (ride != null) {
            model.addAttribute("ride", ride);
            return "rideDetail";
        }
        return "redirect:/rides";
    }

    @PostMapping("/save")
    public String saveRide(@ModelAttribute Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/rides";
    }

    @PostMapping("/cancel/{id}")
    public String cancelRide(@PathVariable Integer id) {
        rideService.cancelRide(id);
        return "redirect:/rides";
    }

    @GetMapping("/delete/{id}")
    public String deleteRide(@PathVariable Integer id) {
        rideService.deleteRide(id);
        return "redirect:/rides";
    }
}
