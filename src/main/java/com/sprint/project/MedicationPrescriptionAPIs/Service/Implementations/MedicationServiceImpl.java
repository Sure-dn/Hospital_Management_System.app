package com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations;


import com.sprint.project.MedicationPrescriptionAPIs.Entity.MedicationEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Repository.MedicationRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Service.Interfaces.MedicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public MedicationEntity createMedication(MedicationEntity medication) {
        if (medication.getName() == null || medication.getName().isBlank()) {
            throw new IllegalArgumentException("Medication name is required");
        }
        return medicationRepository.save(medication);
    }

    @Override
    public List<MedicationEntity> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public MedicationEntity getMedicationByCode(Integer code) {
        return medicationRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with code: " + code));
    }

    @Override
    public MedicationEntity updateMedication(Integer code, MedicationEntity updated) {
        MedicationEntity existing = getMedicationByCode(code);
        existing.setName(updated.getName());
        existing.setBrand(updated.getBrand());
        existing.setDescription(updated.getDescription());
        return medicationRepository.save(existing);
    }

    @Override
    public void deleteMedication(Integer code) {
        if (!medicationRepository.existsById(code)) {
            throw new IllegalArgumentException("Medication not found with code: " + code);
        }
        medicationRepository.deleteById(code);
    }
}
