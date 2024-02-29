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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")

@Tag(name = "Message", description = "Message management API")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Post a new message", tags = { "rentals", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Message created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@Valid @RequestBody MessageDTO messageDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }

        if(messageDTO.getMessage().length() < 5) {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new MessageResponse(messageService.createMessage(messageDTO).get()));
    }
}