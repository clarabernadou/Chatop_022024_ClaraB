package com.chatop.SpringSecurityAuth.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPictureDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.model.RentalResponse;
import com.chatop.SpringSecurityAuth.services.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "Rental")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Create", description="Create a new rental", tags = { "Rental" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(@Valid @ModelAttribute RentalPictureDTO rentalPictureDTO, Principal principalUser, Errors errors) throws IOException {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalPictureDTO, principalUser).get()));
    }

    @Operation(summary = "Get", description="Get all rentals", tags = { "Rental" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rentals found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDTO.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/rentals")
    public ResponseEntity<RentalResponse> getRentals() {
        RentalResponse rental = rentalService.getRental();

        if(rental == null || rental.getRentals().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rental);
    }

    @Operation(summary = "Get", description="Get a rental by id", tags = { "Rental" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDTO.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable Long id) {
        if(rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rentalService.getRental(id));
    }

    @Operation(summary = "Update", description="Update a rental by id", tags = { "Rental" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @Valid @ModelAttribute RentalDTO rentalDTO, Principal principalUser, Errors errors) {
        if(errors.hasErrors() || rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.updateRental(id, rentalDTO, principalUser).get()));
    }
}