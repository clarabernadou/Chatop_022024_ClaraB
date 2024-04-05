package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthDTO {

    @Hidden
    @Schema(description = "Auth's id", example = "1")
    private Long id;

    @Schema(description = "Auth's email", example = "toto@test.fr")
    @NotEmpty
    private String email;

    @Schema(description = "Auth's password", example = "lemotdepassedetoto")
    @NotEmpty
    private String password;
}