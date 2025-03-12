package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Images extends AbstractEntity{
    private String type;
    private String name;

    @Lob
    @Column(name="imagedata", length = 1000)
    private byte[] images;
}
