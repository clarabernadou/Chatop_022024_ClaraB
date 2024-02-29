package com.chatop.SpringSecurityAuth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "users")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auth's id", example = "1")
    private Long id;

    @OneToMany(mappedBy = "owner")
    @Schema(description = "Auth's rentals", example = "1")
    private Set<Rental> rentals;

    @Schema(description = "Auth's email", example = "test@test.fr")
    private String email;

    @Schema(description = "Auth's name", example = "Michel")
    private String name;

    @Schema(description = "Auth's password", example = "password123")
    private String password;

    @Column(updatable = false)
    @Schema(description = "Auth's created at", example = "2021-01-01")
    private LocalDate createdAt;

    @Schema(description = "Auth's updated at", example = "2021-01-01")
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
