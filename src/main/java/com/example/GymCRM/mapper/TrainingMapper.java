package com.example.GymCRM.mapper;

import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.dto.TrainingDTO;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainingTypeMapper.class, TraineeMapper.class})
public interface TrainingMapper {

    @Mapping(source = "trainingType", target = "trainingType")
    @Mapping(source = "trainee", target = "trainee")
    @Mapping(source = "trainer", target = "trainer")
    Training toEntity(TrainingDTO trainingDTO);


    @Mapping(source = "trainingType", target = "trainingType")
    @Mapping(source = "trainee", target = "trainee")
    @Mapping(source = "trainer", target = "trainer")
    TrainingDTO toDTO(Training training);

    @Mapping(source = "trainee", target = "trainee")
    @Mapping(source = "trainingType", target = "trainingType")
    @Mapping(source = "trainer", target = "trainer")
    List<TrainingDTO> toDtoList(List<Training> trainings);


}
