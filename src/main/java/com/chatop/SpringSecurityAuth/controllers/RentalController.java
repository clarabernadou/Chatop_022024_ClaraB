package com.chatop.SpringSecurityAuth.controllers;

import java.util.List;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.services.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")

@Tag(name = "Rental", description = "Rental management APIs")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Post a new Rental", tags = { "rentals", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(@Valid @RequestBody RentalDTO rentalDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalDTO).get()));
    }

    @Operation(summary = "Get all Rentals", tags = { "rentals", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of Rentals", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalDTO.class)))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO>> getRentals() {
        List<RentalDTO> rentals = rentalService.getRentals();

        if(rentals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rentals);
    }

    @Operation(summary = "Get a Rental by ID", tags = { "rentals", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable Long id) {
        if(rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rentalService.getRental(id));
    }

    @Operation(summary = "Update a Rental by ID", tags = { "rentals", "put" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @Valid @RequestBody RentalDTO rentalDTO, Errors errors) {
        if(rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.updateRental(id, rentalDTO).get()));
    }
}
