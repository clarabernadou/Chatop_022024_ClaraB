package com.chatop.SpringSecurityAuth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.SpringSecurityAuth.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
    Optional<Message> findById(Long id);
}
