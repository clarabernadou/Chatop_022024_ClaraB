package com.chatop.SpringSecurityAuth.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.PropertyMap;

import com.chatop.SpringSecurityAuth.dto.MessageDTO;
import com.chatop.SpringSecurityAuth.entity.Message;
import com.chatop.SpringSecurityAuth.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;

        PropertyMap<MessageDTO, Message> messageMap = new PropertyMap<MessageDTO, Message>() {
            @Override
            protected void configure() {
                map().setId(source.getUserId());
            }
        };
        this.modelMapper.addMappings(messageMap);
    }

    public Optional<String> createMessage(MessageDTO messageDTO) {
        Message message = modelMapper.map(messageDTO, Message.class);
        messageRepository.save(message);
        return Optional.of("Message send with success");
    }
}
