package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    @NotEmpty
    @Schema(description = "User's id", example = "1")
    private Long id;

    @NotEmpty
    @Schema(description = "User's name", example = "Michel")
    private String name;

    @NotEmpty
    @Schema(description = "User's email", example = "test@test.fr")
    private String email;
}