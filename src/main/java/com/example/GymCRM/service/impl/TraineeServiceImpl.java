package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.UserDTO;
import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.entity.User;
import com.example.GymCRM.mapper.TraineeMapper;
import com.example.GymCRM.mapper.UserMapper;
import com.example.GymCRM.repository.TraineeRepository;
import com.example.GymCRM.service.interfaces.TraineeService;
import com.example.GymCRM.service.interfaces.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;

    private final UserService userService;

    private final TraineeMapper traineeMapper;

    private final UserMapper userMapper;

    private final SessionFactory sessionFactory;


    public TraineeServiceImpl(TraineeRepository traineeRepository, UserService userService, TraineeMapper traineeMapper, UserMapper userMapper, SessionFactory sessionFactory) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
        this.traineeMapper = traineeMapper;
        this.userMapper = userMapper;
        this.sessionFactory = sessionFactory;
    }


    public List<TraineeDTO> getAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();
        return traineeMapper.toDtoList(trainees);
    }

    public TraineeDTO getTraineeById(Long id) {
        Optional<Trainee> traineeOptional = traineeRepository.findById(id);
        return traineeOptional.map(traineeMapper::traineeToTraineeDTO).orElse(null);
    }

    public Optional<TraineeDTO> getTraineeByIdOptional(Long id) {
        Optional<Trainee> userOptional = traineeRepository.findById(id);
        return userOptional.map(traineeMapper::traineeToTraineeDTO);
    }

    public TraineeDTO createTrainee(TraineeDTO traineeDTO) {
        Long userId = traineeDTO.getUser().getId();

        Optional<UserDTO> userOptional = userService.getUserById(userId);
        UserDTO userDTO = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trainee trainee = traineeMapper.traineeDTOToTrainee(traineeDTO);
        trainee.setUser(userMapper.toEntity(userDTO)); // Assuming UserMapper is defined

        Trainee savedTrainee = traineeRepository.save(trainee);
        return traineeMapper.traineeToTraineeDTO(savedTrainee);
    }


    public TraineeDTO updateTrainee(Long id, TraineeDTO traineeDTO) {
        Optional<Trainee> traineeOptional = traineeRepository.findById(id);
        if (traineeOptional.isPresent()) {
            Trainee traineeToUpdate = traineeMapper.traineeDTOToTrainee(traineeDTO);
            traineeToUpdate.setId(id); // Set ID for the existing entity
            Trainee updatedTrainee = traineeRepository.save(traineeToUpdate);
            return traineeMapper.traineeToTraineeDTO(updatedTrainee);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainee(Long id) {
        if (traineeRepository.existsById(id)) {
            traineeRepository.deleteById(id);
        }
        // No need to throw an exception if the trainee does not exist
    }
    @Transactional()
    @Override
    public TraineeDTO findTraineeByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT t FROM Trainee t WHERE t.user.username = :username";
            Query<Trainee> query = session.createQuery(hql, Trainee.class);
            query.setParameter("username", username);
            return traineeMapper.traineeToTraineeDTO(query.getResultList().get(0));
        }
    }

    public Optional<Trainee> getTraineeByUsername(String username) {
        return traineeRepository.findByUsername(username);
    }


}
