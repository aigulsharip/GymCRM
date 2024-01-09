package com.example.GymCRM.service;

import com.example.GymCRM.entity.Training;
import com.example.GymCRM.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    // Create operation
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    // Read operations
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with ID: " + id));
    }

    // Update operation
    public Training updateTraining(Long id, Training trainingDetails) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with ID: " + id));

        training.setTrainee(trainingDetails.getTrainee());
        training.setTrainer(trainingDetails.getTrainer());
        training.setTrainingName(trainingDetails.getTrainingName());
        training.setTrainingType(trainingDetails.getTrainingType());
        training.setTrainingDate(trainingDetails.getTrainingDate());
        training.setTrainingDuration(trainingDetails.getTrainingDuration());

        return trainingRepository.save(training);
    }

    // Delete operation
    public void deleteTraining(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with ID: " + id));

        trainingRepository.delete(training);
    }
}


