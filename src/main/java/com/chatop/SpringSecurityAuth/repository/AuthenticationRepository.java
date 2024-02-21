package com.chatop.SpringSecurityAuth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.SpringSecurityAuth.entity.Auth;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends CrudRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
    Optional<Auth> findById(Long id);
}
