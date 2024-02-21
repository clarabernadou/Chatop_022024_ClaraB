package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.UserDTO;
import com.chatop.SpringSecurityAuth.entity.User;
import com.chatop.SpringSecurityAuth.model.UserResponse;

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
    
    /**
     *
     * @param email
     * @return {@link User}
     */
    UserResponse me(String email);

    /**
     *
     * @param id
     * @return {@link User}
     */
    UserResponse getUser(Integer id);
}
