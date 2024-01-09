package com.example.GymCRM.mapper;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.TrainingTypeDTO;
import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.TrainingType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingTypeMapper {


    @Mapping(source = "trainingTypeName", target = "trainingTypeName")
    TrainingTypeDTO toDto(TrainingType trainingType);

    @Mapping(source = "trainingTypeName", target = "trainingTypeName")
    TrainingType toEntity(TrainingTypeDTO trainingTypeDTO);

    @Mapping(source = "trainingTypeName", target = "trainingTypeName")
    List<TraineeDTO> toDtoList (List<Trainee> tasks);
}
