package com.sprint.project.Treatment.Service;

import java.util.List;

import com.sprint.project.Treatment.Entity.UndergoesEntity;
import com.sprint.project.Treatment.Entity.UndergoesId;

public interface UndergoesService {
	UndergoesEntity assignTreatment(UndergoesEntity undergoes);
	public List<UndergoesEntity> getAllTreatments();
	public List<UndergoesEntity> getTreatmentByPatient(Integer patientId);
	public List<UndergoesEntity> getTreatmentByStay(Integer stayId);
	UndergoesEntity deleteTreatment(UndergoesId id);
}
