package com.sprint.project.treatmentprostayy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.treatmentprostayy.entities.UndergoesEntity;
import com.sprint.project.treatmentprostayy.entities.UndergoesId;

public interface UndergoesRepository extends JpaRepository<UndergoesEntity, UndergoesId> {

	// ✅ CORRECT (match entity field exactly)
    List<UndergoesEntity> findByPhysicianEmployeeId(int employeeId);

    // ✅ CORRECT
    List<UndergoesEntity> findByProceduresCode(int code);
}