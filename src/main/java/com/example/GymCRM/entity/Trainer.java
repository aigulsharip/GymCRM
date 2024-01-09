package com.example.GymCRM.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trainers")
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization")
    private String specialization;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters, setters, constructors
}
