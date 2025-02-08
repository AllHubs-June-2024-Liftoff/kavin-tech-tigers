package TechTigers.BicycleBuddies.models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String author;

    @NotBlank(message = "Comment must not be blank")
    @Size(min = 3, max = 250, message = "Comment must be between 3 and 250 characters.")
    private String content;

    private LocalDateTime timestamp;
    private int likes;

    private Set<String> likedByUsers = new HashSet<>(); // Fix: Initialize likedByUsers

    // Constructor
    public Comment(String author, String content, LocalDateTime timestamp, int likes) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.likedByUsers = new HashSet<>(); // Fix: Ensure initialization
    }

    public Comment() {
        this.likedByUsers = new HashSet<>(); // Fix: Ensure initialization in default constructor
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<String> likedByUsers) {
        this.likedByUsers = (likedByUsers != null) ? likedByUsers : new HashSet<>();
    }

    // Methods for liking comments & removing likes
    public void addLike(String username) {
        if (likedByUsers.add(username)) {
            this.likes++;
        }
    }

    public void removeLike(String username) {
        if (likedByUsers.remove(username)) {
            this.likes--;
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                ", likedByUsers=" + likedByUsers +
                '}';
    }
}
