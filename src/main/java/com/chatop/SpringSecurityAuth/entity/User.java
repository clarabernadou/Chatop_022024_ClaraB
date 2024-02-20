package com.chatop.SpringSecurityAuth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = " updated_at")
    private Timestamp updatedAt;
}
