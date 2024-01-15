package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.UserRepository;
import com.example.GymCRM.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toDto);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setUsername(calculateUsername(userDTO.getFirstName(), userDTO.getLastName()));
        userDTO.setPassword(generatePassword());
        userDTO.setIsActive(true);
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userMapper.toEntity(userDTO);
            userToUpdate.setId(id);
            User updatedUser = userRepository.save(userToUpdate);
            return userMapper.toDto(updatedUser);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        // No need to throw an exception if the user does not exist
    }

    private String calculateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String calculatedUsername = baseUsername;
        int counter = 1;

        while (userRepository.existsByUsername(calculatedUsername)) {
            calculatedUsername = baseUsername + counter++;
        }
        return calculatedUsername;
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
