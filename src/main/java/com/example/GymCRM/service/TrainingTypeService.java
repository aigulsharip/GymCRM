package com.example.GymCRM.service;

import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    // Create operation
    public TrainingType createTrainingType(TrainingType trainingType) {
        return trainingTypeRepository.save(trainingType);
    }

    // Read operations
    public List<TrainingType> getAllTrainingTypes() {
        return trainingTypeRepository.findAll();
    }

    public TrainingType getTrainingTypeById(Long id) {
        return trainingTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));
    }

    // Update operation
    public TrainingType updateTrainingType(Long id, TrainingType trainingTypeDetails) {
        TrainingType trainingType = trainingTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));

        trainingType.setTrainingTypeName(trainingTypeDetails.getTrainingTypeName());

        return trainingTypeRepository.save(trainingType);
    }

    // Delete operation
    public void deleteTrainingType(Long id) {
        TrainingType trainingType = trainingTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));

        trainingTypeRepository.delete(trainingType);
    }
}
