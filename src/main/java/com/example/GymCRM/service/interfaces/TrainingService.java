package com.example.GymCRM.service.interfaces;

import com.example.GymCRM.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {
    List<TrainingDTO> getAllTraining();

    TrainingDTO getTrainingById(Long id);

    TrainingDTO createTraining(TrainingDTO trainingDTO);

    TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO);

    void deleteTrainer(Long id);
}


