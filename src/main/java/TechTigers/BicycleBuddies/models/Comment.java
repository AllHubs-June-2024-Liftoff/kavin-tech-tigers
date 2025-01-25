package TechTigers.BicycleBuddies.models;



import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Comment extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    @NotBlank
    private String author;
    @NotBlank(message = "Comment must not be blank")
    @Size(min =3, max = 250, message= "Comment must be between 3 and 250 characters.")
    private String content;
    private LocalDateTime timestamp;
    private int likes = 0;


    public Comment(){
        this.timestamp = LocalDateTime.now();

    }
    public Comment( String author, String content, LocalDateTime timestamp, int likes) {

        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes= likes;
    }


    public String getAuthor() { return author;}

    public void setAuthor(String author) {this.author = author;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDateTime getTimestamp() { return timestamp;}

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp;}

    public int getLikes() { return likes;}

    public void setLikes(int likes) { this.likes = likes;}
    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride){
        this.ride = ride;
    }

    public void addLike(){
        this.likes++;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                '}';
    }


}
