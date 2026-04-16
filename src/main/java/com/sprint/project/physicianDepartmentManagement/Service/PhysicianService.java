package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

public interface PhysicianService {
	 /**
     * Create a new physician record.
     * @throws DuplicateResourceException if employeeId or SSN already exists
     */
    PhysicianEntity createPhysician(PhysicianEntity physician);

    /**
     * Retrieve all physicians.
     */
    List<PhysicianEntity> getAllPhysicians();

    /**
     * Retrieve a physician by employeeId.
     * @throws ResourceNotFoundException if not found
     */
    PhysicianEntity getPhysicianById(Integer employeeId);

    /**
     * Update an existing physician.
     * @throws ResourceNotFoundException if not found
     */
    PhysicianEntity updatePhysician(Integer employeeId, PhysicianEntity physician);

    /**
     * Delete a physician by employeeId.
     * @throws ResourceNotFoundException if not found
     * @throws InvalidOperationException if physician is head of a department
     */
    void deletePhysician(Integer employeeId);
	
	
     

}
