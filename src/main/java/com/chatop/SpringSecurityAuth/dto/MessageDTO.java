package com.chatop.SpringSecurityAuth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDTO {
    
    @NotEmpty
    private String message;
}
