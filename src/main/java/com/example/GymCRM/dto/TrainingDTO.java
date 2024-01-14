package com.example.GymCRM.dto;

import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.TrainingType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
public class TrainingDTO {

    private Long id;

    private Trainee trainee;

    private Trainer trainer;

    private TrainingType trainingType;

    private String trainingName;

    private Date trainingDate;

    private int trainingDuration;
}