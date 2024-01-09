package com.example.GymCRM.service;

import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.repository.TraineeRepository;
import com.example.GymCRM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }

    public Trainee createTrainee(Trainee trainee) {
        User existingUser = userRepository.findById(trainee.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + trainee.getUserId()));

        trainee.setUserId(existingUser.getId());
        return traineeRepository.save(trainee);
    }
    // Read operations
    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }

    public Trainee getTraineeById(Long id) {
        return traineeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found with ID: " + id));
    }

    // Update operation
    public Trainee updateTrainee(Long id, Trainee traineeDetails) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found with ID: " + id));

        trainee.setDateOfBirth(traineeDetails.getDateOfBirth());
        trainee.setAddress(traineeDetails.getAddress());

        return traineeRepository.save(trainee);
    }

    // Delete operation
    public void deleteTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found with ID: " + id));

        traineeRepository.delete(trainee);
    }
}

