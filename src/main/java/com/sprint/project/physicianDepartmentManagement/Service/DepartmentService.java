package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;


public interface DepartmentService {
	/**
     * Create a new department.
     * @throws DuplicateResourceException if departmentId already exists
     * @throws ResourceNotFoundException if the head physician does not exist
     */
  DepartmentEntity createDepartment(DepartmentEntity department);

    /**
     * Retrieve all departments.
     */
    List<DepartmentEntity> getAllDepartments();

    /**
     * Retrieve a department by departmentId.
     * @throws ResourceNotFoundException if not found
     */
  DepartmentEntity getDepartmentById(Integer departmentId);

    /**
     * Update an existing department.
     * @throws ResourceNotFoundException if not found or head physician not found
     */
  DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity department);

    /**
     * Get the head physician of a department.
     * @throws ResourceNotFoundException if department not found
     */
  PhysicianEntity getDepartmentHead(Integer departmentId);


}
