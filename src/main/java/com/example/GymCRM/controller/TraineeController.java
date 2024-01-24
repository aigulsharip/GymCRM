package com.example.GymCRM.controller;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.service.interfaces.TraineeService;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @GetMapping
    public ResponseEntity<List<TraineeDTO>> getAllTrainees() {
        List<TraineeDTO> trainees = traineeService.getAllTrainees();
        return new ResponseEntity<>(trainees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable Long id) {
        TraineeDTO trainee = traineeService.getTraineeById(id);
        return new ResponseEntity<>(trainee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TraineeDTO> createTrainee(@RequestBody TraineeDTO traineeDTO) {
        TraineeDTO savedTrainee = traineeService.createTrainee( traineeDTO);
        return new ResponseEntity<>(savedTrainee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TraineeDTO> updateTrainee(@PathVariable Long id, @RequestBody TraineeDTO traineeDTO) {
        TraineeDTO updatedTrainee = traineeService.updateTrainee(id, traineeDTO);
        return new ResponseEntity<>(updatedTrainee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Long id) {
        traineeService.deleteTrainee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Trainee> getTraineeByUsername(@PathVariable String username) {
        Optional<Trainee> traineeOptional = traineeService.getTraineeByUsername(username);
        return new ResponseEntity<>(traineeOptional.orElseThrow(() -> new EntityNotFoundException("Trainee not found for username: " + username)), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<TraineeDTO> getTraineeByUsernameHibernate(@PathVariable String username) {
        TraineeDTO trainee = traineeService.findTraineeByUsername(username);
        return new ResponseEntity<>(trainee, HttpStatus.OK);
    }
}
