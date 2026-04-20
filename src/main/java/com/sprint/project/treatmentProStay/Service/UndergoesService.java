package com.sprint.project.treatmentprostay.service;

import java.util.List;

import com.sprint.project.treatmentprostay.DTO.UndergoesRequestDTO;
import com.sprint.project.treatmentprostay.entity.UndergoesId;

public interface UndergoesService {
	UndergoesRequestDTO assignTreatment(UndergoesRequestDTO dto);
	public List<UndergoesRequestDTO> getAllTreatments();
	public List<UndergoesRequestDTO> getTreatmentByPatient(Integer patientId);
	public List<UndergoesRequestDTO> getTreatmentByStay(Integer stayId);
	UndergoesRequestDTO deleteTreatment(UndergoesId id);
}
