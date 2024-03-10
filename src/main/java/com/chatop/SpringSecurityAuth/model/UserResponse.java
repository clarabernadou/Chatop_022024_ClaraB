package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse extends UserDTO {
    
    @Schema(description = "User's created at", example = "2021-08-01")
    private String createdAt;

    @Schema(description = "User's updated at", example = "2021-08-01")
    private String updatedAt;
}