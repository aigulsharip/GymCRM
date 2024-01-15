package com.example.GymCRM.service.interfaces;

import com.example.GymCRM.dto.TraineeDTO;

import java.util.List;
import java.util.Optional;


public interface TraineeService {
    List<TraineeDTO> getAllTrainees();

    TraineeDTO getTraineeById(Long id);

    Optional<TraineeDTO> getTraineeByIdOptional(Long id);

    TraineeDTO createTrainee(TraineeDTO traineeDTO);

    TraineeDTO updateTrainee(Long id, TraineeDTO traineeDTO);

    void deleteTrainee(Long id);
}
