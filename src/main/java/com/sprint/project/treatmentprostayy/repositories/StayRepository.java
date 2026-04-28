package com.sprint.project.treatmentprostayy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentprostayy.entities.StayEntity;

public interface StayRepository extends JpaRepository<StayEntity, Integer> {
	List<StayEntity> findByPatient_Ssn(Integer ssn);
}