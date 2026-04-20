package com.sprint.project.MedicationPrescriptionAPIs.Service;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.PrescriptionRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.DTO.ResponseDTO.PrescriptionResponseDTO;
import com.sprint.project.MedicationPrescriptionAPIs.entity.PrescribesEntity;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

public interface PrescribesService {
	// PrescribesService.java

	    PrescribesEntity createPrescription(PrescriptionRequestDTO dto);
	    PrescribesEntity createPrescription(PrescribesEntity prescription);
	    
	    List<PrescribesEntity> getAllPrescriptions();
	    List<PrescribesEntity> getPrescriptionsByPatient(Integer ssn);
	    List<PrescribesEntity> getPrescriptionsByPhysician(Integer employeeId);
	    List<PrescribesEntity> getPrescriptionsByDateRange(LocalDateTime from, LocalDateTime to);
	    List<PrescribesEntity> getPrescriptionsByMedication(Integer code);
	}
