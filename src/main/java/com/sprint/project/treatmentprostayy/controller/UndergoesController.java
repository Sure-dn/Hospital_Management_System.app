package com.sprint.project.treatmentprostayy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.treatmentprostayy.DTO.ResponseStructure;
import com.sprint.project.treatmentprostayy.DTO.UndergoesRequestDTO;
import com.sprint.project.treatmentprostayy.entities.UndergoesId;
import com.sprint.project.treatmentprostayy.services.UndergoesService;

@RestController
@RequestMapping("/api/treatments")
public class UndergoesController {

    @Autowired
    private UndergoesService undergoesService;

    @PostMapping
    public ResponseEntity<ResponseStructure<UndergoesRequestDTO>> assign(
            @RequestBody UndergoesRequestDTO undergoesRequestdto) {

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Treatment assigned",
                        undergoesService.assignTreatment(undergoesRequestdto)));
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<UndergoesRequestDTO>>> getAll() {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "All treatments",
                        undergoesService.getAllTreatments()));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<ResponseStructure<List<UndergoesRequestDTO>>> getByPatient(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Patient treatments",
                        undergoesService.getTreatmentByPatient(id)));
    }

    @DeleteMapping
    public ResponseEntity<ResponseStructure<UndergoesRequestDTO>> delete(@RequestBody UndergoesId id) {

        UndergoesRequestDTO deleted = undergoesService.deleteTreatment(id);

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Deleted", deleted));
    }
}