package com.chatop.SpringSecurityAuth.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.SpringSecurityAuth.dto.AuthDTO;
import com.chatop.SpringSecurityAuth.entity.Auth;
import com.chatop.SpringSecurityAuth.model.AuthResponse;
import com.chatop.SpringSecurityAuth.model.UserResponse;
import com.chatop.SpringSecurityAuth.repository.AuthenticationRepository;

import java.security.Principal;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{

    private AuthenticationRepository authenticationRepository;

    private ModelMapper modelMapper;

    private JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository, ModelMapper modelMapper,
                                     JWTService jwtService,BCryptPasswordEncoder passwordEncoder) {
        this.authenticationRepository = authenticationRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<String> createUser(AuthDTO userDTO) {

        if(authenticationRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return Optional.empty();
        }
        Auth user = modelMapper.map(userDTO, Auth.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        authenticationRepository.save(user);
        return Optional.of(jwtService.generateToken(userDTO));
    }

    @Override
    public Optional<String> login(AuthDTO userDTO) {
        Optional<Auth> user = authenticationRepository.findByEmail(userDTO.getEmail());
        if(user.isEmpty() || !this.passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }
        return Optional.of(jwtService.generateToken(userDTO));
    }

    @Override
    public AuthResponse me(String email, Principal principalUser) {
        Optional<Auth> user = authenticationRepository.findByEmail(email);

        if(user.isPresent()) {
            return modelMapper.map(user.get(), AuthResponse.class);
        }
        return null;
    }

    @Override
    public UserResponse getUser(Long id) {
        Optional<Auth> user = authenticationRepository.findById(id);
        if(user.isPresent()) {
            return modelMapper.map(user.get(), UserResponse.class);
        }
        return null;
    }
}