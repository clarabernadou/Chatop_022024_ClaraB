package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import com.chatop.SpringSecurityAuth.dto.MessageDTO;

public interface MessageService {
    /**
     *  Create a new message
     * 
     * @param messageDTO
     * @return String
     */
    public Optional<String> createMessage(MessageDTO messageDTO);
}
