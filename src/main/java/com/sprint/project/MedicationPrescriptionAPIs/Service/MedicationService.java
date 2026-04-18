package com.sprint.project.MedicationPrescriptionAPIs.Service;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.MedicationRequestDTO;
import java.util.List;

public interface MedicationService {

    MedicationRequestDTO createMedication(MedicationRequestDTO dto);

    List<MedicationRequestDTO> getAllMedications();

    MedicationRequestDTO getMedicationByCode(Integer code);

    MedicationRequestDTO updateMedication(Integer code, MedicationRequestDTO dto);

    void deleteMedication(Integer code);
}