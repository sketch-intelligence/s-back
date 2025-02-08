package com.website.e_commerce.user.service;

import com.website.e_commerce.user.model.entity.Follower;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.FollowerRepository;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private CustomerEntityRepository userRepository;

    public String followUser(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) {
            return "You cannot follow yourself!";
        }

        UserEntity follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        UserEntity following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User to follow not found"));

        if (followerRepository.existsByFollowerAndFollowing(follower, following)) {
            return "You are already following this user.";
        }

        Follower follow = new Follower(follower, following);
        followerRepository.save(follow);
        return "Followed successfully!";
    }

    public List<UserEntity> getFollowers(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return followerRepository.findByFollowing(user)
                .stream()
                .map(Follower::getFollower)
                .toList();
    }

    public List<UserEntity> getFollowing(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return followerRepository.findByFollower(user)
                .stream()
                .map(Follower::getFollowing)
                .toList();
    }
}
