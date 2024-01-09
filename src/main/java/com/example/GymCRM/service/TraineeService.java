package com.example.GymCRM.service;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.TraineeMapper;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    UserService userService;

    @Autowired
    private TraineeMapper traineeMapper;

    @Autowired
    private UserMapper userMapper;


    public List<TraineeDTO> getAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();
        return traineeMapper.toDtoList(trainees);
    }

    public TraineeDTO getTraineeById(Long id) {
        Optional<Trainee> traineeOptional = traineeRepository.findById(id);
        return traineeOptional.map(traineeMapper::traineeToTraineeDTO).orElse(null);
    }

    public TraineeDTO createTrainee(TraineeDTO traineeDTO) {
        Long userId = traineeDTO.getUser().getId();

        Optional<UserDTO> userOptional = userService.getUserById(userId);
        UserDTO userDTO = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trainee trainee = traineeMapper.traineeDTOToTrainee(traineeDTO);
        trainee.setUser(userMapper.toEntity(userDTO)); // Assuming UserMapper is defined

        Trainee savedTrainee = traineeRepository.save(trainee);
        return traineeMapper.traineeToTraineeDTO(savedTrainee);
    }



    public TraineeDTO updateTrainee(Long id, TraineeDTO traineeDTO) {
        Optional<Trainee> traineeOptional = traineeRepository.findById(id);
        if (traineeOptional.isPresent()) {
            Trainee traineeToUpdate = traineeMapper.traineeDTOToTrainee(traineeDTO);
            traineeToUpdate.setId(id); // Set ID for the existing entity
            Trainee updatedTrainee = traineeRepository.save(traineeToUpdate);
            return traineeMapper.traineeToTraineeDTO(updatedTrainee);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainee(Long id) {
        if (traineeRepository.existsById(id)) {
            traineeRepository.deleteById(id);
        }
        // No need to throw an exception if the trainee does not exist
    }
}
