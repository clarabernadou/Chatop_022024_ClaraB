package com.chatop.SpringSecurityAuth.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPictureDTO;
import com.chatop.SpringSecurityAuth.model.RentalResponse;

public interface RentalService { 
    /**
     *  Create a new rental
     *
     * @param rentalPictureDTO
     * @return String
     * @throws IOException 
     */
    public Optional<String> createRental(RentalPictureDTO pictureDTO, Principal principalUser) throws IOException;

    /**
     *  Get all rentals
     *
     * @return RentalResponse
     */
    public RentalResponse getRental();

    /**
     *  Get rental by id
     * 
     * @param id
     * @return Optional<RentalDTO>
     */
    public Optional<RentalDTO> getRental(Long id);

    /**
     *  Update rental by id
     * 
     * @param id
     * @param rentalDTO
     * @return String
     */
    public Optional<String> updateRental(Long id, RentalDTO rentalDTO, Principal principalUser);
}