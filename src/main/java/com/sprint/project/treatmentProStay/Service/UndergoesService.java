package com.sprint.project.treatmentProStay.Service;

import java.util.List;

import com.sprint.project.treatmentProStay.DTO.UndergoesRequestDTO;
import com.sprint.project.treatmentProStay.Entity.UndergoesId;

public interface UndergoesService {
	UndergoesRequestDTO assignTreatment(UndergoesRequestDTO dto);
	public List<UndergoesRequestDTO> getAllTreatments();
	public List<UndergoesRequestDTO> getTreatmentByPatient(Integer patientId);
	public List<UndergoesRequestDTO> getTreatmentByStay(Integer stayId);
	UndergoesRequestDTO deleteTreatment(UndergoesId id);
}
