package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterDTO extends AuthDTO {

    @Schema(description = "Auth's name", example = "toto")
    @NotEmpty
    private String name;
}
