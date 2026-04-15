package com.sprint.project.Treatment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.Treatment.Entity.Procedures;

public interface ProceduresRepository extends JpaRepository<Procedures, Integer> {
	
}