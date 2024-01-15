package com.example.GymCRM.service.interfaces;

import com.example.GymCRM.dto.TrainingTypeDTO;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeService {
    TrainingTypeDTO save(TrainingTypeDTO trainingTypeDTO);
    Optional<TrainingTypeDTO> findById(Long id);
    List<TrainingTypeDTO> findAll();
    TrainingTypeDTO updateTrainingType(Long id, TrainingTypeDTO updatedTrainingTypeDTO);
    void deleteById(Long id);
}

