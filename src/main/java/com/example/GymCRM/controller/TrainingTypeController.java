package com.example.GymCRM.controller;

import com.example.GymCRM.dto.TrainingTypeDTO;
import com.example.GymCRM.service.interfaces.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-types")
public class TrainingTypeController {

    @Autowired
    private TrainingTypeService trainingTypeService;

    @PostMapping
    public ResponseEntity<TrainingTypeDTO> save(@RequestBody TrainingTypeDTO trainingTypeDTO) {
        return ResponseEntity.ok(trainingTypeService.save(trainingTypeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingTypeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(trainingTypeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrainingTypeDTO>> findAll() {
        return ResponseEntity.ok(trainingTypeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingTypeDTO> updateTrainingType(@PathVariable Long id, @RequestBody TrainingTypeDTO updatedTrainingTypeDTO) {
        TrainingTypeDTO updatedTypeDTO = trainingTypeService.updateTrainingType(id, updatedTrainingTypeDTO);

        if (updatedTypeDTO == null) {
            return ResponseEntity.notFound().build(); // Handle the null scenario
        }

        return ResponseEntity.ok(updatedTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        trainingTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
