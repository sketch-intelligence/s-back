package com.website.e_commerce.user.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "followers", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"}))
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower; // User who follows

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private UserEntity following; // User being followed

    public Follower() {}

    public Follower(UserEntity follower, UserEntity following) {
        this.follower = follower;
        this.following = following;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getFollower() {
        return follower;
    }

    public void setFollower(UserEntity follower) {
        this.follower = follower;
    }

    public UserEntity getFollowing() {
        return following;
    }

    public void setFollowing(UserEntity following) {
        this.following = following;
    }
}
