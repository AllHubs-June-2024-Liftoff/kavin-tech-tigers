package TechTigers.BicycleBuddies.Controllers;

import TechTigers.BicycleBuddies.service.ImagesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//TODO: Add profile information/paths
@Controller
public class ImagesController {
    private ImagesService imagesService;

    @GetMapping()
    public String allImages(Model model){
        model.addAttribute("images", imagesService.getAllImages());
        return "";
    }

    @GetMapping("/upload")
    public String uploadImages(@RequestParam("file")MultipartFile file){
        try {
            imagesService.saveImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteImage(@PathVariable int id){
        try {
            imagesService.deleteImage(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

}
