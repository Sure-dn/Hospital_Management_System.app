package com.sprint.project.treatmentprostay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.treatmentprostay.entity.StayEntity;

public interface StayRepository extends JpaRepository<StayEntity, Integer> {
	
}