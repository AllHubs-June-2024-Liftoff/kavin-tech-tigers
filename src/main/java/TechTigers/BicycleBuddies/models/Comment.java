package TechTigers.BicycleBuddies.models;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Entity
public class Comment {
   // @GeneratedValue
    private int id;
    private String author;
    // @NotBlank(message = "Comment must not be blank")
    //@Size(min =3, max = 250, message= "Comment must be between 3 and 250 characters.")
    private String content;
    private LocalDateTime timestamp;
    private int likes;
    private Set<String> likedByUsers;

    //    constructors
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
    // getters & setters
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
    // to string method
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

//    hashmap & equals method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
