package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.Ride;
import TechTigers.BicycleBuddies.models.data.ConfigRepository;
import TechTigers.BicycleBuddies.models.data.RideInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("map")
public class MapController {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private RideInfoRepository rideInfoRepository;

    @GetMapping("")
    public String showMap(Model model){
        model.addAttribute("mapsApi", configRepository.findById(1).get());
        model.addAttribute("mapId", configRepository.findById(2).get());
        Iterable<Ride> ridesIterable = rideInfoRepository.findAll();
        List<Ride> rides = new ArrayList<>();
        for (Ride currentRide:ridesIterable){
            rides.add(currentRide);
        }
        model.addAttribute("rides",rides);
        return "map";
    }

    @PostMapping("")
    public String submitInfo(@ModelAttribute Ride ride, Model model) {
        try {
            rideInfoRepository.save(ride);
            model.addAttribute("message", "Information submitted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error saving information.");
        }
        return "index";
    }


}
