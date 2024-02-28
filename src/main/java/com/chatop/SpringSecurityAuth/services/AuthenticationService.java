package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.model.AuthResponse;

public interface AuthenticationService {
    /**
     *  Create a new user
     *
     * @param userDTO
     * @return String
     */
    Optional<String>createUser(AuthDTO userDTO);

    /**
     * Check if the user is in the database
     *
     * @param userDTO
     * @return Optional<String>
     */
    Optional<String> login(AuthDTO userDTO);

    
    /**
     *
     * @param email
     * @return {@link Auth}
     */
    AuthResponse me(String email);

    /**
     *
     * @param id
     * @return {@link Auth}
     */
    AuthResponse getUser(Long id);
}
