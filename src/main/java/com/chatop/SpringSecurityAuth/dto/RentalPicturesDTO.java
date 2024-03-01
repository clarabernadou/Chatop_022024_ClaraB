package com.chatop.SpringSecurityAuth.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class RentalPicturesDTO extends RentalDTO {
    @NotEmpty
    @Schema(description = "Rental's pictures", example = "picture.jpg")
    private MultipartFile pictures;
}
