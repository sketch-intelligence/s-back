package com.website.e_commerce.user.controller;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.service.UserEntityService;
import com.website.e_commerce.userproject.UserProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * The {@code CustomerEntityUserController} class is a REST controller that handles HTTP requests related to customer entities.
 * It provides endpoints to retrieve the current authenticated customer and to list all customer entities.
 *
 * <p>This controller uses {@link UserEntityService} to interact with the customer data.</p>
 *
 * @see UserEntityService
 */
@Controller
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }
    /**
     * Retrieves the current authenticated {@link UserEntity} user.
     *
     * <p>This endpoint returns the authenticated customer entity with an HTTP status of {@link HttpStatus#OK OK}.</p>
     *
     * @return a {@link ResponseEntity} containing the authenticated customer entity
     */
    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedCustomerEntityUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentCustomerUser = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(currentCustomerUser);
    }
    /**
     * Retrieves a list of all {@link UserEntity} users.
     *
     * <p>This endpoint returns a list of all customer entities with an HTTP status of {@link HttpStatus#OK OK}.</p>
     *
     * @return a {@link ResponseEntity} containing the list of all customer entities
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        UserEntity user = userEntityService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<UserProjectDto>> getUserProjects(@PathVariable Long userId) {
        List<UserProjectDto> projects = userEntityService.getUserProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserEntity>>allUsers(){
        List<UserEntity> customerUser = userEntityService.allUsers();
        return ResponseEntity.ok(customerUser);
    }
}
