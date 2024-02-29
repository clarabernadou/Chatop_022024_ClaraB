package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User Model Information")
public class UserResponse extends UserDTO {
    private String createdAt;
    private String updatedAt;
}