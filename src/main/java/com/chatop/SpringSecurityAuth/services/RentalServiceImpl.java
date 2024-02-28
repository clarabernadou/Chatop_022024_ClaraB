package com.chatop.SpringSecurityAuth.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.entity.Rental;
import com.chatop.SpringSecurityAuth.entity.Auth;
import com.chatop.SpringSecurityAuth.repository.RentalRepository;
import com.chatop.SpringSecurityAuth.repository.AuthenticationRepository;

import lombok.Data;

@Data
@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    private ModelMapper modelMapper;

    private AuthenticationRepository authenticationRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper, AuthenticationRepository authenticationRepository) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
        this.authenticationRepository = authenticationRepository;
    }

    public Optional<String> createRental(RentalDTO rentalDTO) {
        Rental rental = modelMapper.map(rentalDTO, Rental.class);

        if (rentalDTO.getOwnerId() != null) {
            Optional<Auth> ownerOptional = authenticationRepository.findById(rentalDTO.getOwnerId());
            ownerOptional.ifPresent(rental::setOwner);
        }

        rentalRepository.save(rental);
        return Optional.of("Rental created !");
    }

    public List<RentalDTO> getRentals() {
        Iterable<Rental> rentalsIterable = rentalRepository.findAll();
        List<Rental> rentals = StreamSupport.stream(rentalsIterable.spliterator(), false)
                                            .collect(Collectors.toList());

        List<RentalDTO> rentalDTOs = rentals.stream()
                        .map(rental -> modelMapper.map(rental, RentalDTO.class))
                        .collect(Collectors.toList());

        return rentalDTOs;
    }

    public Optional<RentalDTO> getRental(Long id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        return rentalOptional.map(rental -> modelMapper.map(rental, RentalDTO.class));
    }

    public Optional<String> updateRental(Long id, RentalDTO rentalDTO) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setName(rentalDTO.getName());
            rental.setSurface(rentalDTO.getSurface());
            rental.setPrice(rentalDTO.getPrice());
            rental.setDescription(rentalDTO.getDescription());

            rentalRepository.save(rental);
            return Optional.of("Rental updated !");
        }

        return Optional.empty();
    }
}
