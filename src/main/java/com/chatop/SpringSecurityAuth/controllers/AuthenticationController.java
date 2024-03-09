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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Create", description = "Create a new user and return a token", tags = { "Authentication" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthDTO authDto) {
        Optional<String> token = authenticationService.createUser(authDto);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @Operation(summary = "Login", description = "Login and return a token", tags = { "Authentication" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User logged in", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDto) {
        Optional<String> token = authenticationService.login(authDto);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @Operation(summary = "Me", description = "Return my user information", tags = { "Authentication" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User information", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/auth/me")
    public ResponseEntity<AuthResponse> me(Principal principalUser){
        if(principalUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(authenticationService.me(principalUser.getName()));
    }

    @Operation(summary = "User", description = "Return the user information", tags = { "Authentication" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User information", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse user = authenticationService.getUser(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }
}