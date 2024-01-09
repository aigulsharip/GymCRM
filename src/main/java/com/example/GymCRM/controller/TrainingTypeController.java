package com.example.GymCRM.controller;

import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-types")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;

    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingType>> getAllTrainingTypes() {
        return ResponseEntity.ok(trainingTypeService.getAllTrainingTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingType> getTrainingTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingTypeService.getTrainingTypeById(id));
    }

    @PostMapping
    public ResponseEntity<TrainingType> createTrainingType(@RequestBody TrainingType trainingType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingTypeService.createTrainingType(trainingType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingType> updateTrainingType(@PathVariable Long id, @RequestBody TrainingType trainingType) {
        return ResponseEntity.ok(trainingTypeService.updateTrainingType(id, trainingType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingType(@PathVariable Long id) {
        trainingTypeService.deleteTrainingType(id);
        return ResponseEntity.noContent().build();
    }
}
