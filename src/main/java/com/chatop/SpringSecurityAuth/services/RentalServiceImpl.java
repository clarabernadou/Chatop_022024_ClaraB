package com.chatop.SpringSecurityAuth.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;
import com.chatop.SpringSecurityAuth.dto.RentalPictureDTO;
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

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadPath = Paths.get("src/main/resources/static/images/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return serverUrl + "/images/" + fileName;
    }

    public Optional<String> createRental(RentalPictureDTO rentalPictureDTO) throws IOException {
        Rental rental = modelMapper.map(rentalPictureDTO, Rental.class);

        if (rentalPictureDTO.getOwnerId() != null) {
            Optional<Auth> ownerOptional = authenticationRepository.findById(rentalPictureDTO.getOwnerId());
            ownerOptional.ifPresent(rental::setOwner);
        }

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