package com.sprint.project.physicianDepartmentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedWithEntity;


public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWithEntity, AffiliatedId> {

	boolean existsByAffiliatedId(AffiliatedId id);

    // Get all affiliations of a physician
    List<AffiliatedWithEntity> findByPhysicianEmployeeId(Integer physicianId);

    //  Get all affiliations of a department
    List<AffiliatedWithEntity> findByDepartmentDepartmentId(Integer departmentId);
}
	


