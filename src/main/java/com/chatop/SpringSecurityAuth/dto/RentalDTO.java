package com.chatop.SpringSecurityAuth.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

    private Long id;

    @NotEmpty
    @Schema(description = "Rental's name", example = "dream house")
    private String name;

    @NotNull
    @Schema(description = "Rental's surface", example = "400")
    private Integer surface;

    @NotNull
    @Schema(description = "Rental's price", example = "1200")
    private Integer price;

    @NotEmpty
    @Schema(description = "Rental's description", example = "This is a description.")
    private String description;

    @NotNull
    @Schema(description = "Rental's owner id", example = "1")
    private Long ownerId;

    @JsonProperty("created_at")
    @Schema(description = "Rental's creation date", example = "2021-09-01")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    @Schema(description = "Rental's update date", example = "2021-09-02")
    private LocalDate updatedAt;
}
