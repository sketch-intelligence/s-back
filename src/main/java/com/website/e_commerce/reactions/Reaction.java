package com.website.e_commerce.reactions;

import com.website.e_commerce.post.Post;
import com.website.e_commerce.user.model.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Reaction(Long id, ReactionType reactionType, UserEntity user, Post post) {
        this.id = id;
        this.reactionType = reactionType;
        this.user = user;
        this.post = post;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public UserEntity getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}










