package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.UserDTO;

public interface AuthenticationService {
    /**
     *  Create a new user
     *
     * @param userDTO
     * @return String
     */
    Optional<String>createUser(UserDTO userDTO);

    /**
     * Check if the user is in the database
     *
     * @param userDTO
     * @return Optional<String>
     */
    Optional<String> login(UserDTO userDTO);
}
