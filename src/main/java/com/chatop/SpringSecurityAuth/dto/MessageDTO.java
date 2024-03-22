package com.chatop.SpringSecurityAuth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {

    @Schema(description = "Message", example = "Hello, this is a message.")
    @NotEmpty
    private String message;

    @Schema(description = "User's id", example = "1")
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

    @Schema(description = "Rental's id", example = "1")
    @JsonProperty("rental_id")
    @NotNull
    private Integer rentalId;
}
