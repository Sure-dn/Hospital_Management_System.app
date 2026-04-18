package com.sprint.project.MedicationPrescriptionAPIs.Controller;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.MedicationRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.DTO.ResponseDTO.MedicationResponseDTO;
import com.sprint.project.MedicationPrescriptionAPIs.Service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public ResponseEntity<MedicationResponseDTO> createMedication(
            @Valid @RequestBody MedicationRequestDTO dto) {     // ← Fixed: now using RequestDTO

        MedicationRequestDTO entity = new MedicationRequestDTO(
                dto.getCode(), dto.getName(), dto.getBrand(), dto.getDescription());

        MedicationRequestDTO saved = medicationService.createMedication(entity);

        MedicationResponseDTO response = new MedicationResponseDTO(
                saved.getCode(), saved.getName(), saved.getBrand(), saved.getDescription());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponseDTO>> getAllMedications() {
        List<MedicationResponseDTO> list = medicationService.getAllMedications().stream()
                .map(m -> new MedicationResponseDTO(
                        m.getCode(), m.getName(), m.getBrand(), m.getDescription()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{code}")
    public ResponseEntity<MedicationResponseDTO> getMedicationByCode(@PathVariable Integer code) {
        MedicationRequestDTO m = medicationService.getMedicationByCode(code);

        MedicationResponseDTO dto = new MedicationResponseDTO(
                m.getCode(), m.getName(), m.getBrand(), m.getDescription());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{code}")
    public ResponseEntity<MedicationResponseDTO> updateMedication(
            @PathVariable Integer code,
            @Valid @RequestBody MedicationRequestDTO dto) {

        MedicationRequestDTO updatedEntity = new MedicationRequestDTO(
                code, dto.getName(), dto.getBrand(), dto.getDescription());

        MedicationRequestDTO updated = medicationService.updateMedication(code, updatedEntity);

        MedicationResponseDTO response = new MedicationResponseDTO(
                updated.getCode(), updated.getName(), updated.getBrand(), updated.getDescription());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Integer code) {
        medicationService.deleteMedication(code);
        return ResponseEntity.noContent().build();
    }
}