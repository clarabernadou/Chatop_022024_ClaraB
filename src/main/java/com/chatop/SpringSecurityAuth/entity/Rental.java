package com.chatop.SpringSecurityAuth.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer surface;

    private Integer price;

    @Column(length = 500)
    private String description;

    private String pictureURL;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Auth owner;

    @Column(updatable = false, name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
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