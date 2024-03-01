package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "Auth Model Information")
public class AuthResponse extends AuthDTO {

    @NotEmpty
    @Schema(description = "Auth's id", example = "1")
    private Long id;
    
    @NotEmpty
    @Schema(description = "Auth's name", example = "Michel")
    private String name;
   
    @Schema(description = "Auth's created date", example = "2021-09-01")
    private String createdAt;

    @Schema(description = "Auth's updated date", example = "2021-09-01")
    private String updatedAt;
}
