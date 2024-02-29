package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Auth Model Information")
public class AuthResponse extends AuthDTO {
    @Schema(description = "Auth's created date", example = "2021-09-01")
    private String createdAt;

    @Schema(description = "Auth's updated date", example = "2021-09-01")
    private String updatedAt;
}
