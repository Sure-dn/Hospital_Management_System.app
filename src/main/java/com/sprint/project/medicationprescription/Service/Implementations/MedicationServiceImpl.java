package com.sprint.project.medicationprescription.Service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.medicationprescription.DTO.RequestDTO.MedicationRequestDTO;
import com.sprint.project.medicationprescription.Service.MedicationService;
import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.repository.MedicationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    // 🔁 DTO → ENTITY
    private MedicationEntity mapToEntity(MedicationRequestDTO dto) {
        MedicationEntity entity = new MedicationEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // 🔁 ENTITY → DTO
    private MedicationRequestDTO mapToDTO(MedicationEntity entity) {
        MedicationRequestDTO dto = new MedicationRequestDTO();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public MedicationRequestDTO createMedication(MedicationRequestDTO dto) {

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Medication name is required");
        }

        MedicationEntity saved = medicationRepository.save(mapToEntity(dto));

        return mapToDTO(saved);
    }

    @Override
    public List<MedicationRequestDTO> getAllMedications() {
        return medicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicationRequestDTO getMedicationByCode(Integer code) {
        MedicationEntity entity = medicationRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found"));

        return mapToDTO(entity);
    }

    @Override
    public MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO dto) {

        MedicationEntity existing = medicationRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found"));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }

        if (dto.getBrand() != null) {
            existing.setBrand(dto.getBrand());
        }

        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription());
        }

        return mapToDTO(medicationRepository.save(existing));
    }

    @Override
    public void deleteMedication(Integer code) {
        if (!medicationRepository.existsById(code)) {
            throw new IllegalArgumentException("Medication not found");
        }
        medicationRepository.deleteById(code);
    }
}