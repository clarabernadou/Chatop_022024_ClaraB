package com.chatop.SpringSecurityAuth.model;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;

import lombok.Data;

@Data
public class AuthResponse extends AuthDTO {

    private String createdAt;

    private String updatedAt;
}
