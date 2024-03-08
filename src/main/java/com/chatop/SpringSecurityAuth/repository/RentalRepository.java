package com.chatop.SpringSecurityAuth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.SpringSecurityAuth.entity.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long>{
    Optional<Rental> findById(Long id);
    Iterable<Rental> findAll();
}