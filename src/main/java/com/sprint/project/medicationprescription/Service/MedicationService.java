package com.sprint.project.medicationprescription.Service;

import java.util.List;

import com.sprint.project.medicationprescription.DTO.RequestDTO.MedicationRequestDTO;

public interface MedicationService {

    MedicationRequestDTO createMedication(MedicationRequestDTO dto);

    List<MedicationRequestDTO> getAllMedications();

    MedicationRequestDTO getMedicationByCode(Integer code);

    MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO dto);

    void deleteMedication(Integer code);
}