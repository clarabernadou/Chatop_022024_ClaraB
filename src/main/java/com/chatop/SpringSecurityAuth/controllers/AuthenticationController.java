package com.chatop.SpringSecurityAuth.controllers;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.model.TokenResponse;
import com.chatop.SpringSecurityAuth.model.UserResponse;
import com.chatop.SpringSecurityAuth.model.AuthResponse;
import com.chatop.SpringSecurityAuth.services.AuthenticationService;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthDTO authDto, Errors errors) {
        Optional<String> token = errors.hasErrors() ? Optional.empty() : authenticationService.createUser(authDto);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDto, Errors errors) {
        Optional<String> token = errors.hasErrors() ? Optional.empty() : authenticationService.login(authDto);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<AuthResponse> me(Principal principalUser){
        if(principalUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(authenticationService.me(principalUser.getName()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse user = authenticationService.getUser(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }
}