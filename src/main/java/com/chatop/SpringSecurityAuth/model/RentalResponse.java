package com.chatop.SpringSecurityAuth.model;

import java.util.List;

import com.chatop.SpringSecurityAuth.dto.RentalDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalResponse {

    private List<RentalDTO> rentals;
}
