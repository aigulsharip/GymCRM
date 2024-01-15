package com.example.GymCRM.controller;

import com.example.GymCRM.controller.TraineeController;
import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.service.interfaces.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraineeControllerTest {

    @Mock
    private TraineeService traineeService;

    @InjectMocks
    private TraineeController traineeController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testGetAllTrainees() {
        // Mocking
        TraineeDTO traineeDTO1 = new TraineeDTO();
        TraineeDTO traineeDTO2 = new TraineeDTO();
        List<TraineeDTO> traineeDTOList = Arrays.asList(traineeDTO1, traineeDTO2);
        Mockito.when(traineeService.getAllTrainees()).thenReturn(traineeDTOList);

        // Test
        ResponseEntity<List<TraineeDTO>> result = traineeController.getAllTrainees();

        // Verification
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(traineeDTOList, result.getBody());
    }

    @Test
    void testGetTraineeById() {
        // Mocking
        Long traineeId = 1L;
        TraineeDTO traineeDTO = new TraineeDTO();
        Mockito.when(traineeService.getTraineeById(traineeId)).thenReturn(traineeDTO);

        // Test
        ResponseEntity<TraineeDTO> result = traineeController.getTraineeById(traineeId);

        // Verification
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(traineeDTO, result.getBody());
    }

    @Test
    void testCreateTrainee() {
        // Mocking
        TraineeDTO traineeDTO = new TraineeDTO();
        TraineeDTO savedTraineeDTO = new TraineeDTO();
        Mockito.when(traineeService.createTrainee(traineeDTO)).thenReturn(savedTraineeDTO);

        // Test
        ResponseEntity<TraineeDTO> result = traineeController.createTrainee(traineeDTO);

        // Verification
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(savedTraineeDTO, result.getBody());
    }

    @Test
    void testUpdateTrainee() {
        // Mocking
        Long traineeId = 1L;
        TraineeDTO traineeDTO = new TraineeDTO();
        TraineeDTO updatedTraineeDTO = new TraineeDTO();
        Mockito.when(traineeService.updateTrainee(traineeId, traineeDTO)).thenReturn(updatedTraineeDTO);

        // Test
        ResponseEntity<TraineeDTO> result = traineeController.updateTrainee(traineeId, traineeDTO);

        // Verification
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedTraineeDTO, result.getBody());
    }

    @Test
    void testDeleteTrainee() {
        // Mocking
        Long traineeId = 1L;

        // Test
        ResponseEntity<Void> result = traineeController.deleteTrainee(traineeId);

        // Verification
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(traineeService).deleteTrainee(traineeId);
    }
}
