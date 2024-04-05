package com.chatop.SpringSecurityAuth.services;

import java.security.Principal;
import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.dto.RegisterDTO;
import com.chatop.SpringSecurityAuth.entity.Auth;
import com.chatop.SpringSecurityAuth.model.AuthResponse;
import com.chatop.SpringSecurityAuth.model.UserResponse;

public interface AuthenticationService {
    /**
     *  Create a new user
     *
     * @param registerDTO
     * @return String
     */
    Optional<String>createUser(RegisterDTO registerDTO);

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
    AuthResponse me(String email, Principal principalUser);

    /**
     *
     * @param id
     * @return {@link Auth}
     */
    UserResponse getUser(Long id);
}
