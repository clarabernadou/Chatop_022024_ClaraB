package com.chatop.SpringSecurityAuth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {

    @NotEmpty
    private String message;

    @NotNull
    private Integer userId;
}
