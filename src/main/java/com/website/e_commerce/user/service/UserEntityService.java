package com.website.e_commerce.user.service;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityService {
    private final CustomerEntityRepository customerEntityRepository;

    public UserEntityService(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
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
