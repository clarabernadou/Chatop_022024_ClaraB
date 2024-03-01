package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthDTO {

    @NotEmpty
    @NotNull
    @Schema(description = "Auth's email", example = "test@test.fr")
    private String email;

    @NotEmpty
    @NotNull
    @Schema(description = "Auth's password", example = "password123")
    private String password;
}