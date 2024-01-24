package com.example.GymCRM.service.interfaces;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.TrainerDTO;

import java.util.List;
import java.util.Optional;


public interface TrainerService {
    List<TrainerDTO> getAllTrainers();

    TrainerDTO getTrainerById(Long id);

    Optional<TrainerDTO> getTrainerByIdOptional(Long id);

    TrainerDTO createTrainer(TrainerDTO trainerDTO);

    TrainerDTO updateTrainer(Long id, TrainerDTO trainerDTO);

    void deleteTrainer(Long id);

    TrainerDTO findTrainerByUsername(String username);

}
