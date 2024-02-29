package com.chatop.SpringSecurityAuth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Token Model Information")
public class TokenResponse {
    @Schema(description = "Token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9")
    private String token;
}
