package com.sprint.project.treatmentProStay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentProStay.Entity.UndergoesEntity;
import com.sprint.project.treatmentProStay.Entity.UndergoesId;

public interface UndergoesRepository extends JpaRepository<UndergoesEntity, UndergoesId> {
	
}