package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.models.data.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map")
public class MapController {

    @Autowired
    private ConfigRepository configRepository;

    @GetMapping("")
    public String showMap(Model model){
        model.addAttribute("mapsApi", configRepository.findById(1).get());
        return "map";
    }
}
