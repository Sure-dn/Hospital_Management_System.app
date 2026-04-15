package com.sprint.project.Treatment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.Treatment.Entity.StayEntity;

public interface StayRepository extends JpaRepository<StayEntity, Integer> {
	
}