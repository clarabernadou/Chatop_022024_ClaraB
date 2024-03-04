package com.chatop.SpringSecurityAuth.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;

public class RentalPicturesDTO extends RentalDTO {

    @Transient
    @Schema(description = "Rental's pictures", example = "picture.jpg")
    private MultipartFile[] pictures;
}
