package com.chatop.SpringSecurityAuth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "owner")
    private Set<Rental> rentals;

    @OneToMany(mappedBy = "userId")
    private Set<Message> messages;

    private String email;

    private String name;

    private String password;

    @Column(updatable = false)
    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
