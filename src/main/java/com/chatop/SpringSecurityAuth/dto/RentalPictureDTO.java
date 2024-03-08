package com.chatop.SpringSecurityAuth.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalPictureDTO extends RentalDTO {
    private MultipartFile picture;
}