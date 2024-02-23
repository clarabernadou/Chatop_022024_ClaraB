package com.chatop.SpringSecurityAuth.controllers;

import java.util.List;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.services.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(@Valid @RequestBody RentalDTO rentalDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalDTO).get()));
    }

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO>> getRentals() {
        List<RentalDTO> rentals = rentalService.getRentals();
        return ResponseEntity.ok(rentals);
    }
}