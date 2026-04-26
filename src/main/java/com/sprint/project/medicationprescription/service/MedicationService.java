package com.sprint.project.medicationprescription.service;

import java.util.List;

import com.sprint.project.medicationprescription.dto.requestdto.MedicationRequestDTO;

public interface MedicationService {

    MedicationRequestDTO createMedication(MedicationRequestDTO dto);

    List<MedicationRequestDTO> getAllMedications();

    MedicationRequestDTO getMedicationByCode(Integer code);

    MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO dto);

    void deleteMedication(Integer code);
}