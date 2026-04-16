package com.sprint.project.physicianDepartmentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;


public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWithEntity, AffiliatedId> {
	boolean existsByAffiliatedId(AffiliatedId affiliatedId);

    List<AffiliatedWithEntity> findByPhysician_PhysicianId(Integer physicianId);

    List<AffiliatedWithEntity> findByDepartment_DepartmentId(Integer departmentId);



}
