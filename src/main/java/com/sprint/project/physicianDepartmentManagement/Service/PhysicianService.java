package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Dto.requestdto.PhysicianRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.ResponseStructure;


public interface PhysicianService {
	 /**
     * Create a new physician record.
     * @throws DuplicateResourceException if employeeId or SSN already exists
     */
    ResponseStructure<PhysicianResponseDto> createPhysician(PhysicianRequestDto physician);

    /**
     * Retrieve all physicians.
     */
   ResponseStructure<List<PhysicianResponseDto>> getAllPhysicians();

    /**
     * Retrieve a physician by employeeId.
     * @throws ResourceNotFoundException if not found
     */
   ResponseStructure<PhysicianResponseDto> getPhysicianById(Integer employeeId);

    /**
     * Update an existing physician.
     * @throws ResourceNotFoundException if not found
     */
   ResponseStructure<PhysicianResponseDto> updatePhysician(Integer employeeId, PhysicianRequestDto physician);

    /**
     * Delete a physician by employeeId.
     * @throws ResourceNotFoundException if not found
     * @throws InvalidOperationException if physician is head of a department
     */
    ResponseStructure<String> deletePhysician(Integer employeeId);
	
	
     

}
