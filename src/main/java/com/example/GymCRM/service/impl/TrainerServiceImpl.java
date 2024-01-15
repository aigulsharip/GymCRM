package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.mapper.TrainerMapper;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TrainerRepository;
import com.example.GymCRM.repository.TrainingTypeRepository;
import com.example.GymCRM.service.interfaces.TrainerService;
import com.example.GymCRM.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private UserMapper userMapper;

    public List<TrainerDTO> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainerMapper.toDtoList(trainers);
    }

    public TrainerDTO getTrainerById(Long id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        return trainerOptional.map(trainerMapper::toDTO).orElse(null);
    }

    public Optional<TrainerDTO> getTrainerByIdOptional(Long id) {
        Optional<Trainer> userOptional = trainerRepository.findById(id);
        return userOptional.map(trainerMapper::toDTO);
    }



    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {

        // Fetch or map specialization based on some criteria, for example:
        TrainingType specialization = trainingTypeRepository.findById(trainerDTO.getTrainingTypeDTO().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialization ID"));

        Long userId = trainerDTO.getUser().getId();
        Optional<UserDTO> userOptional = userService.getUserById(userId);
        UserDTO userDTO = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        trainer.setUser(userMapper.toEntity(userDTO)); // Assuming UserMapper is defined
        trainer.setSpecialization(specialization);

        Trainer savedTrainer = trainerRepository.save(trainer);

        return trainerMapper.toDTO(savedTrainer);
    }




    public TrainerDTO updateTrainer(Long id, TrainerDTO trainerDTO) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if (trainerOptional.isPresent()) {
            Trainer trainerToUpdate = trainerMapper.toEntity(trainerDTO);
            trainerToUpdate.setId(id); // Set ID for the existing entity
            Trainer updatedTrainer = trainerRepository.save(trainerToUpdate);
            return trainerMapper.toDTO(updatedTrainer);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainer(Long id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
        }
        // No need to throw an exception if the trainer does not exist
    }
}
