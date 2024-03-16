package com.chatop.SpringSecurityAuth.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPictureDTO;
import com.chatop.SpringSecurityAuth.entity.Rental;
import com.chatop.SpringSecurityAuth.entity.Auth;
import com.chatop.SpringSecurityAuth.repository.RentalRepository;
import com.cloudinary.utils.ObjectUtils;
import com.chatop.SpringSecurityAuth.repository.AuthenticationRepository;

import lombok.Data;

@Data
@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    private ModelMapper modelMapper;

    private AuthenticationRepository authenticationRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Value("${server.url}")
    private String serverUrl; 
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper, AuthenticationRepository authenticationRepository) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
        this.authenticationRepository = authenticationRepository;
    }

    public String saveImage(MultipartFile file) throws IOException {
        if (file.getSize() > Long.parseLong(maxFileSize)) {
            throw new IOException("File size too large !");
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        return uploadResult.get("url").toString();
    }

    public Optional<String> createRental(RentalPictureDTO rentalPictureDTO, Principal principalUser) throws IOException {
        Rental rental = modelMapper.map(rentalPictureDTO, Rental.class);
        authenticationRepository.findByEmail(principalUser.getName()).ifPresent(rental::setOwner);

        saveImage(rentalPictureDTO.getPicture());
        String pictureURL = saveImage(rentalPictureDTO.getPicture());

        rental.setPictureURL(pictureURL);
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

    public Optional<RentalPictureDTO> getRental(Long id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            RentalPictureDTO rentalPictureDTO = modelMapper.map(rental, RentalPictureDTO.class);
            return Optional.of(rentalPictureDTO);
        }

        return Optional.empty();
    }

    public Optional<String> updateRental(Long id, RentalDTO rentalDTO, Principal principalUser) {
        Optional<Auth> user = authenticationRepository.findByEmail(principalUser.getName());
        if (user.isEmpty()) {
            return Optional.empty();
        }

        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if (user.get().getId() != rentalOptional.get().getOwner().getId()) {
            return Optional.empty();
        }

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