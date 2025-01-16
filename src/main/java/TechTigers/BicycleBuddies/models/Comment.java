package TechTigers.BicycleBuddies.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment extends AbstractEntity{
   @GeneratedValue
    private int id;

    private String author;

    @NotBlank(message = "Comment must not be blank")
    @Size(min =3, max = 250, message= "Comment must be between 3 and 250 characters.")
    private String content;
    private LocalDateTime timestamp;
    private int likes;
    private Set<String> likedByUsers;

    public Comment(){
        this.likedByUsers = new HashSet<>();
    }

    public Comment(int id, String author, String content, LocalDateTime timestamp, int likes) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public int getId() { return id; }

    public String getAuthor() { return author;}

    public void setAuthor(String author) {this.author = author;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDateTime getTimestamp() { return timestamp;}

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp;}

    public int getLikes() { return likes;}

    public void setLikes(int likes) { this.likes = likes;}

    public Set<String> getLikedByUsers() { return likedByUsers;}

    public void setLikedByUsers(Set<String> likedByUsers) { this.likedByUsers = likedByUsers;}

    //    methods for liking comments & removing likes
    public void addLike(String username){
        if(likedByUsers.add(username)){
            this.likes++;
        }
    }
    public void removeLike(String username){
        if(likedByUsers.remove(username)){
            this.likes--;
        }
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                ", likedByUsers=" + likedByUsers +
                '}';
    }


}
