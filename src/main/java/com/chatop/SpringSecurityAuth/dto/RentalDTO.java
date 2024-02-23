package com.chatop.SpringSecurityAuth.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer surface;

    @NotNull
    private Integer price;

    @NotEmpty
    private String description;

    @NotNull
    private Long ownerId;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;
}
