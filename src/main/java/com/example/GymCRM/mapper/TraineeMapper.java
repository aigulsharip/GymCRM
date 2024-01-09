package com.example.GymCRM.mapper;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class }) // Assuming you also have a UserMapper
public interface TraineeMapper {


    @Mapping(source = "user", target = "user")
    TraineeDTO traineeToTraineeDTO(Trainee trainee);

    @Mapping(source = "user", target = "user")
    Trainee traineeDTOToTrainee(TraineeDTO traineeDTO);

    @Mapping(source = "user", target = "user")
    List<TraineeDTO> toDtoList (List<Trainee> tasks);
}
