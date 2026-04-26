package com.sprint.project.patientAppointment.controller;

import com.sprint.project.patientAppointment.dto.requestdto.PatientRequestDTO;
import com.sprint.project.patientAppointment.dto.responsedto.PatientResponseDTO;
import com.sprint.project.patientAppointment.service.PatientService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

     
    // Create a new patient.
    
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO dto) {
        PatientResponseDTO created = patientService.createPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //  GET /api/patients 
    // Retrieve all patients.
     
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // GET /api/patients/{ssn}
    // Retrieve a single patient by SSN.
     
    @GetMapping("/{ssn}")
    public ResponseEntity<PatientResponseDTO> getPatientBySsn(@PathVariable Integer ssn) {
        return ResponseEntity.ok(patientService.getPatientById(ssn));
    }

    //  PUT /api/patients/{ssn} 
    // Update an existing patient's details.
     
    @PutMapping("/{ssn}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Integer ssn,
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.updatePatient(ssn, dto));
    }

    //  DELETE /api/patients/{ssn} 
    //Delete a patient by SSN
      
     
    @DeleteMapping("/{ssn}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer ssn) {
        patientService.deletePatient(ssn);
        return ResponseEntity.noContent().build();
    }

    //  GET /api/patients/{ssn}/pcp
    // Get the Primary Care Physician (PCP) ID for a patient.
     
    @GetMapping("/{ssn}/pcp")
    public ResponseEntity<Integer> getPatientPcp(@PathVariable Integer ssn) {
        return ResponseEntity.ok(patientService.getPCP(ssn));
    }

    //  PUT /api/patients/{ssn}/pcp/{physicianId}
    // Assign or update the PCP for a patient.
    
    @PutMapping("/{ssn}/pcp/{physicianId}")
    public ResponseEntity<PatientResponseDTO> updatePatientPcp(
            @PathVariable Integer ssn,
            @PathVariable Integer physicianId) {
        return ResponseEntity.ok(patientService.updatePCP(ssn, physicianId));
    }
}

