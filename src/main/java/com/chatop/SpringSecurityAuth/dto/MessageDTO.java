package com.chatop.SpringSecurityAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDTO {
    
    @NotEmpty
    @Schema(description = "Message", example = "This is a message.")
    private String message;
}
