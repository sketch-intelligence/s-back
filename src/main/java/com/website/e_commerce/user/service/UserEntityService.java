package com.website.e_commerce.user.service;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.userproject.UserProject;
import com.website.e_commerce.userproject.UserProjectDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.website.e_commerce.userproject.UserProjectRepository;
import com.website.e_commerce.userproject.UserProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserProjectMapper userProjectMapper; // ✅ Spring will now inject it properly


    private final CustomerEntityRepository customerEntityRepository;
    private final UserProjectRepository userProjectRepository;

    public UserEntityService(CustomerEntityRepository customerEntityRepository,UserProjectRepository userProjectRepository) {
        this.customerEntityRepository = customerEntityRepository;
        this.userProjectRepository = userProjectRepository;

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
    public UserEntity getUserById(Long userId) {
        return customerEntityRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
