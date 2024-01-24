package com.example.GymCRM.repository;

import com.example.GymCRM.entity.Trainee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    @Query("SELECT t FROM Trainee t WHERE t.user.username = :username")
    Optional<Trainee> findByUsername(@Param("username") String username);
}
