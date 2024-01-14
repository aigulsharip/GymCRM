package com.example.GymCRM.mapper;

import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainingTypeMapper.class})
public interface TrainerMapper {


    @Mapping(source = "trainingTypeDTO", target = "specialization")
    @Mapping(source = "user", target = "user")
    Trainer toEntity(TrainerDTO trainerDTO);

    @Mapping(source = "specialization", target = "trainingTypeDTO")
    @Mapping(source = "user", target = "user")
    TrainerDTO toDTO(Trainer trainer);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "trainingTypeDTO", target = "trainingTypeDTO")
    List<TrainerDTO> toDtoList(List<Trainer> trainees);

    // Additional mapping methods if needed
}

