package com.chatop.SpringSecurityAuth.controllers;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.dto.AuthNameDTO;
import com.chatop.SpringSecurityAuth.entity.Auth;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", tags = { "auth", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthNameDTO authNameDTO, Errors errors) {
        Optional<String> token = errors.hasErrors() ? Optional.empty() : authenticationService.createUser(authNameDTO);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @Operation(summary = "Login a user", tags = { "auth", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDTO authDto, Errors errors) {
        Optional<String> token = errors.hasErrors() ? Optional.empty() : authenticationService.login(authDto);

        if(token.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new TokenResponse(token.get()));
    }

    @Operation(summary = "Get user details", tags = { "auth", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/auth/me")
    public ResponseEntity<AuthResponse> me(Principal principalUser){
        if(principalUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(authenticationService.me(principalUser.getName()));
    }

    @Operation(summary = "Get user by ID", tags = { "auth", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Auth.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse user = authenticationService.getUser(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(user);
    }
}