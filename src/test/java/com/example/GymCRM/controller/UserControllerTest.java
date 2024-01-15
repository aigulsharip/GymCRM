package com.example.GymCRM.controller;

import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetAllUsers() {
        // Mocking
        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        List<UserDTO> userDTOList = Arrays.asList(userDTO1, userDTO2);
        Mockito.when(userService.getAllUsers()).thenReturn(userDTOList);

        // Test
        ResponseEntity<List<UserDTO>> result = userController.getAllUsers();

        // Verification
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userDTOList, result.getBody());
    }

    @Test
    void testGetUserById() {
        // Mocking
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        Optional<UserDTO> userOptional = Optional.of(userDTO);
        Mockito.when(userService.getUserById(userId)).thenReturn(userOptional);

        // Test
        ResponseEntity<UserDTO> result = userController.getUserById(userId);

        // Verification
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userDTO, result.getBody());
    }

    @Test
    void testGetUserByIdNotFound() {
        // Mocking
        Long userId = 1L;
        Optional<UserDTO> userOptional = Optional.empty();
        Mockito.when(userService.getUserById(userId)).thenReturn(userOptional);

        // Test
        ResponseEntity<UserDTO> result = userController.getUserById(userId);

        // Verification
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void testCreateUser() {
        // Mocking
        UserDTO userDTO = new UserDTO();
        UserDTO savedUserDTO = new UserDTO();
        Mockito.when(userService.saveUser(userDTO)).thenReturn(savedUserDTO);

        // Test
        ResponseEntity<UserDTO> result = userController.createUser(userDTO);

        // Verification
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(savedUserDTO, result.getBody());
    }

    @Test
    void testUpdateUser() {
        // Mocking
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        UserDTO updatedUserDTO = new UserDTO();
        Mockito.when(userService.updateUser(userId, userDTO)).thenReturn(updatedUserDTO);

        // Test
        ResponseEntity<UserDTO> result = userController.updateUser(userId, userDTO);

        // Verification
        assertEquals(updatedUserDTO != null ? HttpStatus.OK : HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(updatedUserDTO, result.getBody());
    }

    @Test
    void testDeleteUser() {
        // Mocking
        Long userId = 1L;

        // Test
        ResponseEntity<Void> result = userController.deleteUser(userId);

        // Verification
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(userService).deleteUser(userId);
    }
}
