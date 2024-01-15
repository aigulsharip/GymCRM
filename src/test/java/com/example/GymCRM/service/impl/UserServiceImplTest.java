package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUsers() {
        // Mocking
        User user1 = new User();
        User user2 = new User();
        List<User> userList = Arrays.asList(user1, user2);
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        List<UserDTO> userDTOList = Arrays.asList(userDTO1, userDTO2);
        Mockito.when(userMapper.toDtoList(userList)).thenReturn(userDTOList);

        // Test
        List<UserDTO> result = userService.getAllUsers();

        // Verification
        assertEquals(userDTOList, result);
    }

    @Test
    void testGetUserById() {
        // Mocking
        Long userId = 1L;
        User user = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO userDTO = new UserDTO();
        Mockito.when(userMapper.toDto(user)).thenReturn(userDTO);

        // Test
        Optional<UserDTO> result = userService.getUserById(userId);

        // Verification
        assertTrue(result.isPresent());
        assertEquals(userDTO, result.get());
    }

    @Test
    void testSaveUser() {
        // Mocking
        UserDTO userDTO = new UserDTO();
        User user = new User();
        Mockito.when(userMapper.toEntity(userDTO)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO savedUserDTO = new UserDTO();
        Mockito.when(userMapper.toDto(user)).thenReturn(savedUserDTO);

        // Test
        UserDTO result = userService.saveUser(userDTO);

        // Verification
        assertNotNull(result);
        assertEquals(savedUserDTO, result);
    }

    @Test
    void testUpdateUser() {
        // Mocking
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        User userToUpdate = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userToUpdate));
        Mockito.when(userMapper.toEntity(userDTO)).thenReturn(userToUpdate);
        Mockito.when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);

        UserDTO updatedUserDTO = new UserDTO();
        Mockito.when(userMapper.toDto(userToUpdate)).thenReturn(updatedUserDTO);

        // Test
        UserDTO result = userService.updateUser(userId, userDTO);

        // Verification
        assertNotNull(result);
        assertEquals(updatedUserDTO, result);
    }

    @Test
    void testDeleteUser() {
        // Mocking
        Long userId = 1L;
        Mockito.when(userRepository.existsById(userId)).thenReturn(true);

        // Test
        userService.deleteUser(userId);

        // Verification
        Mockito.verify(userRepository).deleteById(userId);
    }
}
