package com.sprint.project.MedicationPrescriptionAPIs.Service.Interfaces;

import com.sprint.project.MedicationPrescriptionAPIs.Entity.MedicationEntity;
import java.util.List;

public interface MedicationService {
    MedicationEntity createMedication(MedicationEntity medication);
    List<MedicationEntity> getAllMedications();
    MedicationEntity getMedicationByCode(Integer code);
    MedicationEntity updateMedication(Integer code, MedicationEntity medication);
    void deleteMedication(Integer code);
}