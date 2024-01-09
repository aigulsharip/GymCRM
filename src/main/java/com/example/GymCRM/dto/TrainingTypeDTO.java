package com.example.GymCRM.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TrainingTypeDTO {
    private Long id;
    private String trainingTypeName;

}
