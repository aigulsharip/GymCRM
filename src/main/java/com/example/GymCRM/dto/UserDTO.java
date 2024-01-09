package com.example.GymCRM.dto;

import lombok.Data;

// UserDTO.java
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive;

}
