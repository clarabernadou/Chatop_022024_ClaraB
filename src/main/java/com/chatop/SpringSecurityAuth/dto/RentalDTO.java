package com.chatop.SpringSecurityAuth.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

    @Hidden
    @Schema(description = "Rental's id", example = "1")
    private Long id;

    @Schema(description = "Rental's name", example = "House")
    @NotEmpty
    private String name;

    @Schema(description = "Rental's surface", example = "1000")
    @NotNull
    private Integer surface;

    @Schema(description = "Rental's price", example = "10000")
    @NotNull
    private Integer price;

    @Schema(description = "Rental's description", example = "This is a house.")
    @NotEmpty
    private String description;

    @Schema(description = "Rental's picture URL", example = "http://localhost:8080/api/rentals/images/image.JPEG")
    @JsonProperty("picture")
    private String pictureURL;

    @Schema(description = "Owner's id", example = "1")
    @JsonProperty("owner_id")
    private Long ownerId;

    @Hidden
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate createdAt;

    @Hidden
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate updatedAt;
}