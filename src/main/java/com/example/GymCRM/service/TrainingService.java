package com.example.GymCRM.service;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.dto.TrainingDTO;
import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.Training;
import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.mapper.TraineeMapper;
import com.example.GymCRM.mapper.TrainerMapper;
import com.example.GymCRM.mapper.TrainingMapper;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TrainerRepository;
import com.example.GymCRM.repository.TrainingRepository;
import com.example.GymCRM.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;


    @Autowired
    private TraineeService traineeService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private TraineeMapper traineeMapper;

    @Autowired
    private TrainingMapper trainingMapper;



    public List<TrainingDTO> getAllTraining() {
        List<Training> trainers = trainingRepository.findAll();
        return trainingMapper.toDtoList(trainers);
    }

    public TrainingDTO getTrainingById(Long id) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        return trainerOptional.map(trainingMapper::toDTO).orElse(null);
    }



    public TrainingDTO createTraining(TrainingDTO trainingDTO) {

        // Fetch or map specialization based on some criteria, for example:
        TrainingType specialization = trainingTypeRepository.findById(trainingDTO.getTrainingType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialization ID"));

        Long traineeId = trainingDTO.getTrainee().getId();
        Optional<TraineeDTO> traineeOptional = traineeService.getTraineeByIdOptional(traineeId);
        TraineeDTO traineeDTO = traineeOptional.orElseThrow(() -> new IllegalArgumentException("Trainee not found"));


        Long trainerId = trainingDTO.getTrainer().getId();
        Optional<TrainerDTO> trainerOptional = trainerService.getTrainerByIdOptional(trainerId);
        TrainerDTO trainerDTO = trainerOptional.orElseThrow(() -> new IllegalArgumentException("Trainer not found"));


        ///////////////

//        Long userId = trainerDTO.getUser().getId();
//        Optional<UserDTO> userOptional = userService.getUserById(userId);
//        UserDTO userDTO = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        Training training = trainingMapper.toEntity(trainingDTO);
        training.setTrainee(traineeMapper.traineeDTOToTrainee(traineeDTO));
        training.setTrainer(trainerMapper.toEntity(trainerDTO));
        training.setTrainingType(specialization);

//        Trainer trainer = trainerMapper.toEntity(trainerDTO);
//        trainer.setUser(userMapper.toEntity(userDTO)); // Assuming UserMapper is defined
//        trainer.setSpecialization(specialization);

//        Trainer savedTrainer = trainerRepository.save(trainer);
        Training savedTraining = trainingRepository.save(training);


        return trainingMapper.toDTO(savedTraining);
    }




    public TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        if (trainerOptional.isPresent()) {
            Training trainerToUpdate = trainingMapper.toEntity(trainingDTO);
            trainerToUpdate.setId(id); // Set ID for the existing entity
            Training updatedTrainer = trainingRepository.save(trainerToUpdate);
            return trainingMapper.toDTO(updatedTrainer);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainer(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        }
        // No need to throw an exception if the trainer does not exist
    }



}


