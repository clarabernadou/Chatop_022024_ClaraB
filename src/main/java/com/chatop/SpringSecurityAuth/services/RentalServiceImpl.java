package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.entity.Rental;
import com.chatop.SpringSecurityAuth.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    private ModelMapper modelMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<String> createRental(RentalDTO rentalDTO) {
        Rental rental = modelMapper.map(rentalDTO, Rental.class);
        rentalRepository.save(rental);
        return Optional.of("Rental created !");
    }
}
