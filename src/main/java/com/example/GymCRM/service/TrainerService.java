package com.example.GymCRM.service;

import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.User;
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

    public Trainer createTrainer(Trainer trainer) {
        User user = userService.getUserById(trainer.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        trainer.setUser(user);
        return trainerRepository.save(trainer);
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
