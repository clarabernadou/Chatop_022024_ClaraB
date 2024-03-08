package com.chatop.SpringSecurityAuth.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPictureDTO;

public interface RentalService { 
    /**
     *  Create a new rental
     *
     * @param rentalPictureDTO
     * @return String
     * @throws IOException 
     */
    public Optional<String> createRental(RentalPictureDTO pictureDTO) throws IOException;

    /**
     *  Get all rentals
     *
     * @return List<RentalDTO>
     */
    public List<RentalDTO> getRentals();

    /**
     *  Get rental by id
     * 
     * @param id
     * @return Optional<RentalDTO>
     */
    public Optional<RentalPictureDTO> getRental(Long id);

    /**
     *  Update rental by id
     * 
     * @param id
     * @param rentalDTO
     * @return String
     */
    public Optional<String> updateRental(Long id, RentalDTO rentalDTO);
}