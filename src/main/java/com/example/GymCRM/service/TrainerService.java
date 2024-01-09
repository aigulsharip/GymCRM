package com.example.GymCRM.service;

import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {


    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    public Trainer createTrainer(Trainer trainer) {
        Optional<UserDTO> userOptional = userService.getUserById(trainer.getUser().getId());
        if (userOptional.isPresent()) {
            User user = userMapper.toEntity(userOptional.get());
            trainer.setUser(user);
            return trainerRepository.save(trainer);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }
    // Read operations
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }


    // Update operation
    public Trainer updateTrainer(Long id, Trainer trainerDetails) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + id));

        trainer.setSpecialization(trainerDetails.getSpecialization());

        return trainerRepository.save(trainer);
    }

    // Delete operation
    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + id));

        trainerRepository.delete(trainer);
    }
}
