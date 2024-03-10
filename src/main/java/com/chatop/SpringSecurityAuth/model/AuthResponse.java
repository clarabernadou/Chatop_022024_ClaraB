package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthResponse extends AuthDTO {

    @Schema(description = "Auth's created at", example = "2021-08-01")
    private String createdAt;

    @Schema(description = "Auth's updated at", example = "2021-08-01")
    private String updatedAt;
}
