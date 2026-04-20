package com.sprint.project.treatmentprostayy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentprostayy.entities.StayEntity;

public interface StayRepository extends JpaRepository<StayEntity, Integer> {
	
}