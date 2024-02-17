package com.chatop.SpringSecurityAuth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.SpringSecurityAuth.entity.User;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
