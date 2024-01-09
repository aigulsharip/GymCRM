package com.example.GymCRM.dto;


import lombok.Data;

@Data
public class TrainerDTO {

    private Long id;
    private TrainingTypeDTO trainingTypeDTO;
    private UserDTO user;

}
