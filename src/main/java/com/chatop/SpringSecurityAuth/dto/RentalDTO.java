package com.chatop.SpringSecurityAuth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

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
}
