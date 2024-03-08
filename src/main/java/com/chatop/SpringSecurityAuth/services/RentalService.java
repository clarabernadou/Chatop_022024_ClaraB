package com.chatop.SpringSecurityAuth.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPicturesDTO;

public interface RentalService { 
    /**
     *  Create a new rental
     *
     * @param rentalPicturesDTO
     * @return String
     * @throws IOException 
     */
    public Optional<String> createRental(RentalPicturesDTO picturesDTO) throws IOException;

    /**
     *  Get all rentals
     *
     * @return List<RentalDTO>
     */
    public List<RentalPicturesDTO> getRentals();

    /**
     *  Get rental by id
     * 
     * @param id
     * @return Optional<RentalDTO>
     */
    public Optional<RentalPicturesDTO> getRental(Long id);

    /**
     *  Update rental by id
     * 
     * @param id
     * @param rentalDTO
     * @return String
     */
    public Optional<String> updateRental(Long id, RentalDTO rentalDTO);
}
