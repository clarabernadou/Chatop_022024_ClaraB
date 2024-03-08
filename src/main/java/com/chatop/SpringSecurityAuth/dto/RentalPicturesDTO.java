package com.chatop.SpringSecurityAuth.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RentalPicturesDTO extends RentalDTO {

    @Schema(description = "Rental's picture", example = "picture.jpg")
    private MultipartFile picture;
}
