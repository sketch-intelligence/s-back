package com.website.e_commerce.user.controller;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followers")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/add")
    public String followUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        return followerService.followUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public List<UserEntity> getFollowers(@PathVariable Long userId) {
        return followerService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<UserEntity> getFollowing(@PathVariable Long userId) {
        return followerService.getFollowing(userId);
    }
}
