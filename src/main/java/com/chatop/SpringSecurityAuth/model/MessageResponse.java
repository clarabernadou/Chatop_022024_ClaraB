package com.chatop.SpringSecurityAuth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Message Model Information")
public class MessageResponse {
    @Schema(description = "Message", example = "This is a message.")
    private String message;
}