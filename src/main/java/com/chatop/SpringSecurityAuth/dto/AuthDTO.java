package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthDTO {

    @NotEmpty
    @Schema(description = "Auth's email", example = "test@test.fr")
    private String email;

    @NotEmpty
    @Schema(description = "Auth's name", example = "Michel")
    private String name;

    @NotEmpty
    @Schema(description = "Auth's password", example = "password123")
    private String password;
}