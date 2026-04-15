package com.sprint.project.Treatment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.Treatment.Entity.Undergoes;
import com.sprint.project.Treatment.Entity.UndergoesId;

public interface UndergoesRepository extends JpaRepository<Undergoes, UndergoesId> {
	
}