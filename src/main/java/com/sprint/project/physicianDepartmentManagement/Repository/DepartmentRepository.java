package com.sprint.project.physicianDepartmentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
	
   DepartmentEntity createDepartment(DepartmentEntity department);
   List<DepartmentEntity> getAllDepartments();
   DepartmentEntity getDepartmentById(Integer departmentId);
   DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity updatedDepartment);
   PhysicianEntity getDepartmentHead(Integer departmentId);
  

}
