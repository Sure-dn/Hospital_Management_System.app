package com.sprint.project.MedicationPrescriptionAPIs.Controller;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.PrescriptionRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.DTO.ResponseDTO.PrescriptionResponseDTO;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Service.PrescribesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescribesService prescribesService;

 // PrescriptionController.java
    @PostMapping
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(@Valid @RequestBody PrescriptionRequestDTO dto) {
        // Now this matches the logic implemented in your ServiceImpl
        PrescribesEntity saved = prescribesService.createPrescription(dto); 
        PrescriptionResponseDTO response = toResponseDTO(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDTO>> getAllPrescriptions() {
        List<PrescriptionResponseDTO> list = prescribesService.getAllPrescriptions().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/patient/{ssn}")
    public ResponseEntity<List<PrescriptionResponseDTO>> getPrescriptionsByPatient(@PathVariable Integer ssn) {
        List<PrescriptionResponseDTO> list = prescribesService.getPrescriptionsByPatient(ssn).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/physician/{employeeId}")
    public ResponseEntity<List<PrescriptionResponseDTO>> getPrescriptionsByPhysician(@PathVariable Integer employeeId) {
        List<PrescriptionResponseDTO> list = prescribesService.getPrescriptionsByPhysician(employeeId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/medication/{code}")
    public ResponseEntity<List<PrescriptionResponseDTO>> getPrescriptionsByMedication(@PathVariable Integer code) {
        List<PrescriptionResponseDTO> list = prescribesService.getPrescriptionsByMedication(code).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<PrescriptionResponseDTO>> getPrescriptionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<PrescriptionResponseDTO> list = prescribesService.getPrescriptionsByDateRange(from, to).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private PrescriptionResponseDTO toResponseDTO(PrescribesEntity entity) {
        PrescriptionResponseDTO dto = new PrescriptionResponseDTO();
        dto.setPhysicianId(entity.getPhysician().getEmployeeId());
        dto.setPhysicianName(entity.getPhysician().getName());
        dto.setPatientSsn(entity.getPatient().getSsn());
        dto.setPatientName(entity.getPatient().getName());
        dto.setMedicationCode(entity.getMedication().getCode());
        dto.setMedicationName(entity.getMedication().getName());
        dto.setDate(entity.getDate());
        dto.setAppointmentId(entity.getAppointment() != null ? entity.getAppointment().getAppointmentId() : null);
        dto.setDose(entity.getDose());
        return dto;
    }
}