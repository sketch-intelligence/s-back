package com.website.e_commerce.post;

import com.website.e_commerce.comment.Comment;
import com.website.e_commerce.postimage.PostImage;
import com.website.e_commerce.reactions.Reaction;
import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;
    private String text;

    @CreationTimestamp
    @Column(updatable = false, name = "timestamp") // Ensures it won't change after creation
    private LocalDateTime timeStamp;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<PostImage> postImages;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments ;
    @OneToMany(mappedBy = "post" , fetch = FetchType.EAGER)
    private List<Reaction> reactions;


    public Post(Long id, String text, LocalDateTime timeStamp, List<PostImage> postImages, List<Comment> comments, List<Reaction> reactions) {
        this.id = id;
        this.text = text;
        this.timeStamp = timeStamp;
        this.postImages = postImages;
        this.comments = comments;
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                ", postImages=" + postImages +
                ", comments=" + comments +
                ", reactions=" + reactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!Objects.equals(id, post.id)) return false;
        if (!Objects.equals(text, post.text)) return false;
        if (!Objects.equals(timeStamp, post.timeStamp)) return false;
        if (!Objects.equals(postImages, post.postImages)) return false;
        if (!Objects.equals(comments, post.comments)) return false;
        return Objects.equals(reactions, post.reactions);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (postImages != null ? postImages.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (reactions != null ? reactions.hashCode() : 0);
        return result;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Post() {
    }

    public Post(Long id, String text, LocalDateTime timeStamp, List<PostImage> postImages) {
        this.id = id;
        this.text = text;
        this.timeStamp = timeStamp;
        this.postImages = postImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

}
