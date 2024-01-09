package com.example.GymCRM.controller;
import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.entity.Trainer;
import com.example.GymCRM.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        TrainerDTO newTrainer = trainerService.createTrainer(trainerDTO);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        TrainerDTO trainer = trainerService.getTrainerById(id);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @RequestBody TrainerDTO trainerDTO) {
        TrainerDTO updatedTrainer = trainerService.updateTrainer(id, trainerDTO);

        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}

