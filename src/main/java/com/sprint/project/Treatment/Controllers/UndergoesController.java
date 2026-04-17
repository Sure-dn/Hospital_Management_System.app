package com.sprint.project.Treatment.Controllers;

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

import com.sprint.project.Treatment.DTO.ResponseStructure;
import com.sprint.project.Treatment.Entity.UndergoesEntity;
import com.sprint.project.Treatment.Entity.UndergoesId;
import com.sprint.project.Treatment.Service.UndergoesService;

@RestController
@RequestMapping("/api/treatments")
public class UndergoesController {

    @Autowired
    private UndergoesService undergoesService;

    @PostMapping
    public ResponseEntity<ResponseStructure<UndergoesEntity>> assign(
            @RequestBody UndergoesEntity entity) {

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Treatment assigned",
                        undergoesService.assignTreatment(entity)));
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<UndergoesEntity>>> getAll() {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "All treatments",
                        undergoesService.getAllTreatments()));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<ResponseStructure<List<UndergoesEntity>>> getByPatient(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Patient treatments",
                        undergoesService.getTreatmentByPatient(id)));
    }

    @DeleteMapping
    public ResponseEntity<ResponseStructure<UndergoesEntity>> delete(@RequestBody UndergoesId id) {

        UndergoesEntity deleted = undergoesService.deleteTreatment(id);

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Deleted", deleted));
    }
}