package com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.MedicationRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.Service.MedicationService;
import com.sprint.project.MedicationPrescriptionAPIs.entity.MedicationEntity;
import com.sprint.project.MedicationPrescriptionAPIs.repository.MedicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    // ✅ DTO -> Entity
    private MedicationEntity mapToEntity(MedicationRequestDTO dto) {
        MedicationEntity entity = new MedicationEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // ✅ Entity -> DTO
    private MedicationRequestDTO mapToDTO(MedicationEntity entity) {
        MedicationRequestDTO dto = new MedicationRequestDTO();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public MedicationRequestDTO createMedication(MedicationRequestDTO medication) {
        if (medication.getName() == null || medication.getName().isBlank()) {
            throw new IllegalArgumentException("Medication name is required");
        }

        MedicationEntity entity = mapToEntity(medication);
        MedicationEntity saved = medicationRepository.save(entity);

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
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with code: " + code));

        return mapToDTO(entity);
    }

    @Override
    public MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO updated) {
        MedicationEntity existing = medicationRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with code: " + code));

        existing.setName(updated.getName());
        existing.setBrand(updated.getBrand());
        existing.setDescription(updated.getDescription());

        MedicationEntity saved = medicationRepository.save(existing);
        return mapToDTO(saved);
    }

    @Override
    public void deleteMedication(Integer code) {
        if (!medicationRepository.existsById(code)) {
            throw new IllegalArgumentException("Medication not found with code: " + code);
        }
        medicationRepository.deleteById(code);
    }
}