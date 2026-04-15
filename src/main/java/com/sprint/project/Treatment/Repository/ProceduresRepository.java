package com.sprint.project.Treatment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.Treatment.Entity.ProceduresEntity;

public interface ProceduresRepository extends JpaRepository<ProceduresEntity, Integer> {
	
}