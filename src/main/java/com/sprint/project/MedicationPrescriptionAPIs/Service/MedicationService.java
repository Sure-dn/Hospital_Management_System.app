package com.sprint.project.MedicationPrescriptionAPIs.Service;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.MedicationRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.entity.MedicationEntity;

import java.util.List;

public interface MedicationService {
    MedicationRequestDTO createMedication(MedicationRequestDTO medication);
    List<MedicationRequestDTO> getAllMedications();
    MedicationRequestDTO getMedicationByCode(Integer code);
    MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO medication);
    void deleteMedication(Integer code);
}