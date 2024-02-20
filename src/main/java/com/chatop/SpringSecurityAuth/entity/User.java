package com.chatop.SpringSecurityAuth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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

    @Column(updatable = false)
    private LocalDate created_at;

    private LocalDate updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDate.now();
    }
}
