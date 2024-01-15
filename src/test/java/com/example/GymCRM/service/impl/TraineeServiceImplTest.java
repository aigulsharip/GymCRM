package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.TraineeMapper;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TraineeRepository;
import com.example.GymCRM.service.interfaces.UserService;
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
class TraineeServiceImplTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private UserService userService;

    @Mock
    private TraineeMapper traineeMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    @Test
    void testGetAllTrainees() {
        // Mocking
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        List<Trainee> traineeList = Arrays.asList(trainee1, trainee2);
        Mockito.when(traineeRepository.findAll()).thenReturn(traineeList);

        TraineeDTO traineeDTO1 = new TraineeDTO();
        TraineeDTO traineeDTO2 = new TraineeDTO();
        List<TraineeDTO> traineeDTOList = Arrays.asList(traineeDTO1, traineeDTO2);
        Mockito.when(traineeMapper.toDtoList(traineeList)).thenReturn(traineeDTOList);

        // Test
        List<TraineeDTO> result = traineeService.getAllTrainees();

        // Verification
        assertEquals(traineeDTOList, result);
    }

    @Test
    void testGetTraineeById() {
        // Mocking
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        Mockito.when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

        TraineeDTO traineeDTO = new TraineeDTO();
        Mockito.when(traineeMapper.traineeToTraineeDTO(trainee)).thenReturn(traineeDTO);

        // Test
        TraineeDTO result = traineeService.getTraineeById(traineeId);

        // Verification
        assertNotNull(result);
        assertEquals(traineeDTO, result);
    }

    @Test
    void testUpdateTrainee() {
        // Mocking
        Long traineeId = 1L;
        TraineeDTO traineeDTO = new TraineeDTO();
        Trainee traineeToUpdate = new Trainee();
        Mockito.when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(traineeToUpdate));
        Mockito.when(traineeMapper.traineeDTOToTrainee(traineeDTO)).thenReturn(traineeToUpdate);
        Mockito.when(traineeRepository.save(traineeToUpdate)).thenReturn(traineeToUpdate);

        TraineeDTO updatedTraineeDTO = new TraineeDTO();
        Mockito.when(traineeMapper.traineeToTraineeDTO(traineeToUpdate)).thenReturn(updatedTraineeDTO);

        // Test
        TraineeDTO result = traineeService.updateTrainee(traineeId, traineeDTO);

        // Verification
        assertNotNull(result);
        assertEquals(updatedTraineeDTO, result);
    }

    @Test
    void testDeleteTrainee() {
        // Mocking
        Long traineeId = 1L;
        Mockito.when(traineeRepository.existsById(traineeId)).thenReturn(true);

        // Test
        traineeService.deleteTrainee(traineeId);

        // Verification
        Mockito.verify(traineeRepository).deleteById(traineeId);
    }
}
