package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    @Schema(description = "User's id", example = "1")
    @NotEmpty
    private Long id;

    @Schema(description = "User's name", example = "toto")
    @NotEmpty
    private String name;

    @Schema(description = "User's email", example = "toto@test.fr")
    @NotEmpty
    private String email;
}