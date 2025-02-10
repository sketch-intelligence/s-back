package com.website.e_commerce.user.service;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.userproject.UserProject;
import com.website.e_commerce.userproject.UserProjectDto;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.user.model.entity.Follower;
import com.website.e_commerce.user.repository.FollowerRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.website.e_commerce.userproject.UserProjectRepository;
import com.website.e_commerce.userproject.UserProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserProjectMapper userProjectMapper; // ✅ Spring will now inject it properly


    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    private final UserProjectRepository userProjectRepository;

    private final FollowerRepository followerRepository;


    public UserEntityService(CustomerEntityRepository customerEntityRepository,UserProjectRepository userProjectRepository,FollowerRepository followerRepository) {
        this.customerEntityRepository = customerEntityRepository;
        this.userProjectRepository = userProjectRepository;
        this.followerRepository = followerRepository;
    }



    public List<UserProjectDto> getUserProjects(Long userId) {
        List<UserProject> projects = userProjectRepository.findByArchitectId(userId);
        return userProjectMapper.toDto(projects); // Convert to DTO
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> customers = new ArrayList<>();

        customerEntityRepository.findAll().forEach(customers::add);

        return customers;
    }

    public UserEntity findByEmail(String email) {

        return customerEntityRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
    public UserEntityDto getUserById(Long userId) {
        UserEntity user = customerEntityRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Debug followers
        List<Follower> followersList = followerRepository.findByFollowing(user);
        System.out.println("Followers found: " + followersList.size());

        // Debug following
        List<Follower> followingList = followerRepository.findByFollower(user);
        System.out.println("Following found: " + followingList.size());

        // Mapping
        List<Long> followers = followersList.stream()
                .map(f -> f.getFollower().getId())
                .collect(Collectors.toList());

        List<Long> following = followingList.stream()
                .map(f -> f.getFollowing().getId())
                .collect(Collectors.toList());

        return new UserEntityDto(user.getId(), user.getUsername(), user.getImageUrl(), followers, following);
    }


}
