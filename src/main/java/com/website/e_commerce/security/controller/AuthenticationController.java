package com.website.e_commerce.security.controller;

import com.website.e_commerce.user.RoleEnum;
import com.website.e_commerce.user.model.dto.UserEntityLoginResponse;
import com.website.e_commerce.user.model.dto.LoginUserEntityDto;
import com.website.e_commerce.user.model.dto.RegisterUserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.security.service.AuthenticationService;
import com.website.e_commerce.security.service.JwtService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthenticationController {
    public static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserEntityLoginResponse> registerUser(@Valid @RequestBody RegisterUserEntityDto registerUserDto) {
        registerUserDto.setRole(RoleEnum.USER);
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        String jwtToken = jwtService.generateToken(registeredUser);

        UserEntityLoginResponse response = new UserEntityLoginResponse(
                "User registered successfully",
                jwtToken,
                jwtService.getExpirationTime(),
                registeredUser
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/architect")
    public ResponseEntity<UserEntityLoginResponse> registerArchitect(@Valid @RequestBody RegisterUserEntityDto architectDto) {
        architectDto.setRole(RoleEnum.ARCHITECT);
        UserEntity registeredArchitect = authenticationService.signup(architectDto);

        String jwtToken = jwtService.generateToken(registeredArchitect);

        UserEntityLoginResponse response = new UserEntityLoginResponse(
                "Architect registered successfully",
                jwtToken,
                jwtService.getExpirationTime(),
                registeredArchitect
        );

        return ResponseEntity.ok(response);
    }



    @PostMapping("/authenticate")
    public ResponseEntity<UserEntityLoginResponse> authenticate(@RequestBody LoginUserEntityDto loginUserDto) {
        log.info("POST /auth/login endpoint hit with payload: {}", loginUserDto);

        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        UserEntityLoginResponse response = new UserEntityLoginResponse(
                "User authenticated successfully",
                jwtToken,
                jwtService.getExpirationTime(),
                authenticatedUser
        );

        log.info("User authenticated successfully, token generated.");

        return ResponseEntity.ok(response);
    }



}
