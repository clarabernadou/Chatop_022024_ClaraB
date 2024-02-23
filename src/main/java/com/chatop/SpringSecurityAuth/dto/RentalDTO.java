package com.chatop.SpringSecurityAuth.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer surface;

    @NotNull
    private Integer price;

    @NotEmpty
    private List<String> pictures;

    @NotEmpty
    private String description;

    @NotNull
    private Long ownerId;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;
}
