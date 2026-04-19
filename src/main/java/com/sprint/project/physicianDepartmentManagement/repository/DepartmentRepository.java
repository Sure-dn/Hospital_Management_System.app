package com.sprint.project.physicianDepartmentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
	
//   DepartmentEntity createDepartment(DepartmentEntity department);
//   List<DepartmentEntity> getAllDepartments();
//   DepartmentEntity getDepartmentById(Integer departmentId);
//   DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity updatedDepartment);
//   PhysicianEntity getDepartmentHead(Integer departmentId);
   
// Get department head
   @Query("SELECT d.head FROM DepartmentEntity d WHERE d.departmentId = :departmentId")
   PhysicianEntity findDepartmentHead(Integer departmentId);
   

   // Get departments with no head assigned
   @Query("SELECT d FROM DepartmentEntity d WHERE d.head IS NULL")
   List<DepartmentEntity> findDepartmentsWithoutHead();
  

}
