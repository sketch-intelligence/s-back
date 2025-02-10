package com.website.e_commerce.user.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class UserEntityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageUrl;
    private List<Long> followers;  // List of follower user IDs
    private List<Long> following;


    @Override
    public String toString() {
        return "UserEntityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityDto that = (UserEntityDto) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { // Keep method name consistent
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntityDto() {
    }

    public UserEntityDto(Long id, String username, String profilePictureUrl, List<Long> followers, List<Long> following) {
        this.id = id;
        this.name = username;
        this.imageUrl = profilePictureUrl;
        this.followers = followers;
        this.following = following;
    }

    // ✅ New Constructor (Fix for PostService)
    public UserEntityDto(Long id, String username, String profilePictureUrl) {
        this.id = id;
        this.name = username;
        this.imageUrl = profilePictureUrl;
        this.followers = new ArrayList<>();  // Default empty list
        this.following = new ArrayList<>();  // Default empty list
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
