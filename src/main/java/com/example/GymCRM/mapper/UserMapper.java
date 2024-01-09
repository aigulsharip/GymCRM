package com.example.GymCRM.mapper;

import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface UserMapper {
    UserDTO toDto (User user);
    User toEntity (UserDTO userDTO);

    List<UserDTO> toDtoList (List<User> tasks);
}