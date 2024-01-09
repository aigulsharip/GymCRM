package com.example.GymCRM.controller;
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
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        Trainer newTrainer = trainerService.createTrainer(trainer);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        return trainer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        return ResponseEntity.ok(trainerService.updateTrainer(id, trainer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}

