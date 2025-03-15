package TechTigers.BicycleBuddies.service;

import TechTigers.BicycleBuddies.data.ImagesRepository;
import TechTigers.BicycleBuddies.models.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImagesService {
    @Autowired
    private final ImagesRepository imagesRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public void saveImage(MultipartFile file) throws IOException{
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID() +"_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath);

        Images images = new Images();
        images.setName(file.getOriginalFilename());
        images.setFilePath(filePath.toString());
        imagesRepository.save(images);
    }

    public List<Images> getAllImages(){
        return (List<Images>) imagesRepository.findAll();
    }

    public Images getImage(int id){
        return imagesRepository.findById(id).orElseThrow(()-> new RuntimeException("Image not found"));
    }

    public void deleteImage(int id) throws IOException {
        Images images = imagesRepository.findById(id).orElseThrow(()-> new RuntimeException("Image not found"));
        Path filePath = Paths.get(images.getFilePath());
        Files.deleteIfExists(filePath);
        imagesRepository.delete(images);
    }
}
