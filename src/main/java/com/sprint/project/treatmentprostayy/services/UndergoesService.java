package com.sprint.project.treatmentprostayy.services;

import java.util.List;

import com.sprint.project.treatmentprostayy.DTO.UndergoesRequestDTO;
import com.sprint.project.treatmentprostayy.entities.UndergoesId;

public interface UndergoesService {
	UndergoesRequestDTO assignTreatment(UndergoesRequestDTO dto);
	public List<UndergoesRequestDTO> getAllTreatments();
	public List<UndergoesRequestDTO> getTreatmentByPatient(Integer patientId);
	public List<UndergoesRequestDTO> getTreatmentByStay(Integer stayId);
	UndergoesRequestDTO deleteTreatment(UndergoesId id);
}
