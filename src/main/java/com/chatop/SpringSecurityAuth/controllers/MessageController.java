package com.chatop.SpringSecurityAuth.controllers;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.SpringSecurityAuth.dto.MessageDTO;
import com.chatop.SpringSecurityAuth.model.MessageResponse;
import com.chatop.SpringSecurityAuth.services.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@Valid @RequestBody MessageDTO messageDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        if(messageDTO.getMessage().length() < 5) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.BAD_REQUEST);
        }

        System.out.printf("MESSAGE : " + messageDTO.getMessage(), "USER : " + messageDTO.getUserId(), "RENTAL : " + messageDTO.getRentalId());

        return ResponseEntity.ok(new MessageResponse(messageService.createMessage(messageDTO).get()));
    }
}