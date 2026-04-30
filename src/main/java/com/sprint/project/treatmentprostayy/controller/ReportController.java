package com.sprint.project.treatmentprostayy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.treatmentprostayy.dto.UndergoesResponseDTO;
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
    public List<UndergoesResponseDTO> getByPhysician(@PathVariable int id) {

        List<UndergoesEntity> list = repo.findByPhysicianEmployeeId(id);

        // 🔥 ADD THIS BLOCK
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No procedures found for physician id " + id);
        }

        return list.stream()
                .map(u -> new UndergoesResponseDTO(
                        u.getPatient().getSsn(),
                        u.getProcedures().getCode(),
                        u.getStay().getStayId(),
                        u.getUndergoesId().getDateUndergoes(),
                        u.getPhysician() != null ? u.getPhysician().getEmployeeId() : null,
                        u.getAssistingNurse() != null ? u.getAssistingNurse().getEmployeeId() : null
                ))
                .toList();
    }

    // ✅ Procedure → Patients
    @GetMapping("/procedure/{code}/patients")
    public List<UndergoesResponseDTO> getByProcedure(@PathVariable int code) {

        List<UndergoesEntity> list = repo.findByProceduresCode(code);

        return list.stream()
                .map(u -> new UndergoesResponseDTO(
                        u.getPatient().getSsn(),
                        u.getProcedures().getCode(),
                        u.getStay().getStayId(),
                        u.getUndergoesId().getDateUndergoes(),   // ✅ FIX HERE
                        u.getPhysician() != null ? u.getPhysician().getEmployeeId() : null,
                        u.getAssistingNurse() != null ? u.getAssistingNurse().getEmployeeId() : null
                ))
                .toList();
    }
}