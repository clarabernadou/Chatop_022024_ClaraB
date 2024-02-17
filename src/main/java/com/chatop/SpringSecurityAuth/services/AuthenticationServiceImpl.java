package com.chatop.SpringSecurityAuth.services;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.SpringSecurityAuth.dto.UserDTO;
import com.chatop.SpringSecurityAuth.entity.User;
import com.chatop.SpringSecurityAuth.repository.AuthenticationRepository;

import java.util.Optional;


@Data
@Service
public class AuthenticationServiceImpl implements  AuthenticationService{

    private AuthenticationRepository userRepository;

    private ModelMapper modelMapper;

    private JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationRepository userRepository, ModelMapper modelMapper,
                                     JWTService jwtService,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<String> createUser(UserDTO userDTO) {

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return Optional.empty();
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return Optional.of(jwtService.generateToken(userDTO));
    }
}
