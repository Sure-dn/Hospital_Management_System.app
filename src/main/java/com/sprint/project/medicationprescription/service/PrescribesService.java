package com.sprint.project.medicationprescription.service;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

import com.sprint.project.medicationprescription.dto.requestdto.PrescriptionRequestDTO;
import com.sprint.project.medicationprescription.dto.responsedto.PrescriptionResponseDTO;
import com.sprint.project.medicationprescription.entity.PrescribesEntity;

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
