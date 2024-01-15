package com.example.GymCRM.repository;

import com.example.GymCRM.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}


