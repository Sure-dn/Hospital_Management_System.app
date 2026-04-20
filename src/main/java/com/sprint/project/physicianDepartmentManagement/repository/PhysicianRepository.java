package com.sprint.project.physicianDepartmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;

public interface PhysicianRepository extends JpaRepository<PhysicianEntity, Integer> {
	boolean existsBySsn(Integer ssn);

    boolean existsBySsnAndEmployeeIdNot(Integer ssn, Integer employeeId);

}
