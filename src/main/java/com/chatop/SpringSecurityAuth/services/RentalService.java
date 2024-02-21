package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;

public interface RentalService { 
    /**
     *  Create a new rental
     *
     * @param rentalDTO
     * @return String
     */
    public Optional<String> createRental(RentalDTO rentalDTO);
}
