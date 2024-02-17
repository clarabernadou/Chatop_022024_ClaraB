package com.chatop.SpringSecurityAuth.controllers;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.SpringSecurityAuth.dto.UserDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.model.TokenResponse;
import com.chatop.SpringSecurityAuth.services.AuthenticationService;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, Errors errors) {
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
}