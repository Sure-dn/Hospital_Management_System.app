package com.sprint.project.physicianDepartmentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

}
