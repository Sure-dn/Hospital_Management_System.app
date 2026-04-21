package com.sprint.project.treatmentprostayy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.treatmentprostayy.entities.UndergoesEntity;
import com.sprint.project.treatmentprostayy.repositories.UndergoesRepository;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private UndergoesRepository repo;

    // ✅ Physician → Procedures
    @GetMapping("/physician/{id}/procedures")
    public List<UndergoesEntity> getByPhysician(@PathVariable int id) {
        return repo.findByPhysicianEmployeeId(id);
    }

    // ✅ Procedure → Patients
    @GetMapping("/procedure/{code}/patients")
    public List<UndergoesEntity> getByProcedure(@PathVariable int code) {
        return repo.findByProceduresCode(code);
    }
}