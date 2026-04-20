package com.sprint.project.treatmentprostayy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentprostayy.entities.UndergoesEntity;
import com.sprint.project.treatmentprostayy.entities.UndergoesId;

public interface UndergoesRepository extends JpaRepository<UndergoesEntity, UndergoesId> {
	
}