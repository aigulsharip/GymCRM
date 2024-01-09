package com.example.GymCRM.service;

import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    // Create operation
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    // Read operations
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + id));
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
