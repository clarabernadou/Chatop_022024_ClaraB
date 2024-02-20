package com.chatop.SpringSecurityAuth.entity;

import java.security.Timestamp;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = " updated_at")
    private Timestamp updatedAt;    
}
