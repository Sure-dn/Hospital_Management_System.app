package com.sprint.project.treatmentprostay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentprostay.entity.UndergoesEntity;
import com.sprint.project.treatmentprostay.entity.UndergoesId;

public interface UndergoesRepository extends JpaRepository<UndergoesEntity, UndergoesId> {
	
}