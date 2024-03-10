package com.chatop.SpringSecurityAuth.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RentalPictureDTO extends RentalDTO {
    
    @Schema(description = "Rental's picture", example = "image.JPEG")
    private MultipartFile picture;
}