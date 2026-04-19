package com.sprint.project.treatmentProStay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.treatmentProStay.Entity.StayEntity;

public interface StayRepository extends JpaRepository<StayEntity, Integer> {
	
}