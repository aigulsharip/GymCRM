package com.example.GymCRM.service.interfaces;

import com.example.GymCRM.dto.UserDTO;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

}
