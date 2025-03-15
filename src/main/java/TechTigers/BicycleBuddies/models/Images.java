package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Images extends AbstractEntity{
    private String contentType;
    private String name;
    private String filePath;
    @Lob
    private byte[] images;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}
