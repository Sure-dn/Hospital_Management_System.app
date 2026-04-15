package com.sprint.project.MedicationPrescriptionAPIs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.MedicationEntity;



public interface MedicationRepository extends JpaRepository<MedicationEntity,Integer>  {
	
}
