package com.example.GymCRM.controller;

import com.example.GymCRM.entity.Trainee;
import com.example.GymCRM.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public ResponseEntity<List<Trainee>> getAllTrainees() {
        return ResponseEntity.ok(traineeService.getAllTrainees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id) {
        return ResponseEntity.ok(traineeService.getTraineeById(id));
    }

    @PostMapping
    public ResponseEntity<Trainee> createTrainee(@RequestBody Trainee trainee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(traineeService.createTrainee(trainee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable Long id, @RequestBody Trainee trainee) {
        return ResponseEntity.ok(traineeService.updateTrainee(id, trainee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Long id) {
        traineeService.deleteTrainee(id);
        return ResponseEntity.noContent().build();
    }
}
