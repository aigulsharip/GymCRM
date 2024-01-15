package com.example.GymCRM.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeDTO {
    private Long id;
    private LocalDate dateOfBirth;
    private String address;
    private UserDTO user;

}
