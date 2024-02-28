package com.chatop.SpringSecurityAuth.controllers;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.dto.UserDTO;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthDTO userDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.NOT_FOUND);
        }

        Optional<String> token = authenticationService.createUser(userDTO);
        if (token.isPresent()) {
            System.out.println("Le token de création est " + token.get());
        } else {
            System.out.println("La création de l'utilisateur a échoué");
        }

        if(token.isEmpty()) {
            System.out.println("Le token est vide");
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO userDTO) {

        Optional<String> token = authenticationService.login(userDTO);
        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<AuthResponse> me(Principal principalUser){
        // Ne pas oublier de mettre le Bearer Token dans Postman
        if(principalUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(authenticationService.me(principalUser.getName()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        UserResponse user = authenticationService.getUser(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(user);
    }
}