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
    private UserService userService;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }

    public Trainee createTrainee(Trainee trainee) {
        User user = userService.getUserById(trainee.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        trainee.setUser(user);
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

