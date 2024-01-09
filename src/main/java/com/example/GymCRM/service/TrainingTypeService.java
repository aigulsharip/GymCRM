package com.example.GymCRM.service;

import com.example.GymCRM.dto.TrainingTypeDTO;
import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.mapper.TrainingTypeMapper;
import com.example.GymCRM.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class TrainingTypeService {
//
//    private final TrainingTypeRepository trainingTypeRepository;
//
//    @Autowired
//    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
//        this.trainingTypeRepository = trainingTypeRepository;
//    }
//
//    // Create operation
//    public TrainingType createTrainingType(TrainingType trainingType) {
//        return trainingTypeRepository.save(trainingType);
//    }
//
//    // Read operations
//    public List<TrainingType> getAllTrainingTypes() {
//        return trainingTypeRepository.findAll();
//    }
//
//    public TrainingType getTrainingTypeById(Long id) {
//        return trainingTypeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));
//    }
//
//    // Update operation
//    public TrainingType updateTrainingType(Long id, TrainingType trainingTypeDetails) {
//        TrainingType trainingType = trainingTypeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));
//
//        trainingType.setTrainingTypeName(trainingTypeDetails.getTrainingTypeName());
//
//        return trainingTypeRepository.save(trainingType);
//    }
//
//    // Delete operation
//    public void deleteTrainingType(Long id) {
//        TrainingType trainingType = trainingTypeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Training type not found with ID: " + id));
//
//        trainingTypeRepository.delete(trainingType);
//    }
//}

import java.util.List;
        import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TrainingTypeService  {

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    @Autowired
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeDTO save(TrainingTypeDTO trainingTypeDTO) {
        TrainingType trainingType = trainingTypeMapper.toEntity(trainingTypeDTO);
        return trainingTypeMapper.toDto(trainingTypeRepository.save(trainingType));
    }

    public Optional<TrainingTypeDTO> findById(Long id) {
        return trainingTypeRepository.findById(id).map(trainingTypeMapper::toDto);
    }

    public List<TrainingTypeDTO> findAll() {
        return trainingTypeRepository.findAll().stream().map(trainingTypeMapper::toDto).collect(Collectors.toList());
    }

    public TrainingTypeDTO updateTrainingType(Long id, TrainingTypeDTO updatedTrainingTypeDTO) {
        Optional<TrainingType> optionalTrainingType = trainingTypeRepository.findById(id);

        if (!optionalTrainingType.isPresent()) {
            return null; // or handle differently, maybe throw a custom exception
        }

        TrainingType existingTrainingType = optionalTrainingType.get();
        existingTrainingType.setTrainingTypeName(updatedTrainingTypeDTO.getTrainingTypeName());

        TrainingType updatedTrainingType = trainingTypeRepository.save(existingTrainingType);
        return trainingTypeMapper.toDto(updatedTrainingType);
    }
    public void deleteById(Long id) {
        trainingTypeRepository.deleteById(id);
    }
}

