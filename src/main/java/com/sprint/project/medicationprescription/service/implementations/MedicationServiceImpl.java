package com.sprint.project.medicationprescription.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.medicationprescription.dto.requestdto.MedicationRequestDTO;
import com.sprint.project.medicationprescription.service.MedicationService;
import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.repository.MedicationRepository;

// ✅ IMPORT YOUR EXCEPTIONS
import com.sprint.project.medicationprescription.exception.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    private MedicationEntity mapToEntity(MedicationRequestDTO dto) {
        MedicationEntity entity = new MedicationEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private MedicationRequestDTO mapToDTO(MedicationEntity entity) {
        MedicationRequestDTO dto = new MedicationRequestDTO();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    // ✅ CREATE
    @Override
    public MedicationRequestDTO createMedication(MedicationRequestDTO dto) {

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidMedicationDataException("Medication name is required");
        }

        // Optional duplicate check
        if (medicationRepository.existsById(dto.getCode())) {
            throw new DuplicateMedicationException("Medication already exists with code: " + dto.getCode());
        }

        MedicationEntity saved = medicationRepository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    // ✅ GET ALL
    @Override
    public List<MedicationRequestDTO> getAllMedications() {
        return medicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    @Override
    public MedicationRequestDTO getMedicationByCode(Integer code) {
        MedicationEntity entity = medicationRepository.findById(code)
                .orElseThrow(() -> new MedicationNotFoundException(code));

        return mapToDTO(entity);
    }

    // ✅ UPDATE
    @Override
    public MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO dto) {

        MedicationEntity existing = medicationRepository.findById(code)
                .orElseThrow(() -> new MedicationNotFoundException(code));

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidMedicationDataException("Medication name cannot be empty");
        }

        existing.setName(dto.getName());
        existing.setBrand(dto.getBrand());
        existing.setDescription(dto.getDescription());

        return mapToDTO(medicationRepository.save(existing));
    }

    // ✅ DELETE
    @Override
    public void deleteMedication(Integer code) {
        if (!medicationRepository.existsById(code)) {
            throw new MedicationNotFoundException(code);
        }

        medicationRepository.deleteById(code);
    }
}