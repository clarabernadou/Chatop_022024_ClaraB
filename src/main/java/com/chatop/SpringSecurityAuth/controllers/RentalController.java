package com.chatop.SpringSecurityAuth.controllers;

import java.io.IOException;
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
import com.chatop.SpringSecurityAuth.services.RentalService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "Rental")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(@Valid @ModelAttribute RentalPictureDTO rentalPictureDTO, Errors errors) throws IOException {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalPictureDTO).get()));
    }

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO>> getRentals() {
        List<RentalDTO> rentals = rentalService.getRentals();

        if(rentals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable Long id) {
        if(rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(rentalService.getRental(id));
    }

    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @Valid @ModelAttribute RentalDTO rentalDTO, Errors errors) {
        if(errors.hasErrors() || rentalService.getRental(id) == null) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.updateRental(id, rentalDTO).get()));
    }
}