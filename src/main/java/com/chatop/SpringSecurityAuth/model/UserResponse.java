package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.UserDTO;

import lombok.Data;

@Data
public class UserResponse extends UserDTO {

    private String createdAt;

    private String updatedAt;
}
