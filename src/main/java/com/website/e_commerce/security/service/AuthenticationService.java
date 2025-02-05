package com.website.e_commerce.security.service;

import com.website.e_commerce.role.RoleEntity;
import com.website.e_commerce.role.RoleRepository;
import com.website.e_commerce.user.model.dto.LoginUserEntityDto;
import com.website.e_commerce.user.model.dto.RegisterUserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.website.e_commerce.exception.UserAlreadyExistsException;
import java.util.Set;

@Service
@Validated
public class AuthenticationService {
    private final CustomerEntityRepository  customerEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;
    public AuthenticationService(CustomerEntityRepository customerEntityRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.customerEntityRepository = customerEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public UserEntity signup(@Valid RegisterUserEntityDto input) {
        // Check if the email already exists
        if (customerEntityRepository.existsByEmail(input.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered.");
        }

        // Check if the username already exists (if applicable)
        if (customerEntityRepository.existsByname(input.getName())) {
            throw new UserAlreadyExistsException("Username is already taken.");
        }

        // Fetch the role
        RoleEntity role = roleRepository.findByName(input.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Role"));

        // Create new user entity
        UserEntity userEntity = new UserEntity();
        userEntity.setName(input.getName());
        userEntity.setEmail(input.getEmail());
        userEntity.setRoles(Set.of(role));
        userEntity.setPassword(passwordEncoder.encode(input.getPassword()));

        // Save the new user
        return customerEntityRepository.save(userEntity);
    }

    public UserEntity authenticate(@Valid LoginUserEntityDto input){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail() , input.getPassword()));

         return customerEntityRepository.findByEmail(input.getEmail()).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }


}
