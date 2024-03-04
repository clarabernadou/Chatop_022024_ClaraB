package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthNameDTO extends AuthDTO {

    @NotEmpty
    @NotNull
    @Schema(description = "Auth's name", example = "michel12345")
    private String name;
}
