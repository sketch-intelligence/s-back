package com.website.e_commerce.user.repository;

import com.website.e_commerce.user.model.entity.Follower;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    // Check if a user is already following another user
    boolean existsByFollowerAndFollowing(UserEntity follower, UserEntity following);

    // Get all followers of a user
    List<Follower> findByFollowing(UserEntity following);

    // Get all users that a user follows
    List<Follower> findByFollower(UserEntity follower);
}
