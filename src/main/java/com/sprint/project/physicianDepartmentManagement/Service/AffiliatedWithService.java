package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;

public interface AffiliatedWithService {
	 /**
     * Create an affiliation between a physician and a department.
     * @throws ResourceNotFoundException if physician or department not found
     * @throws DuplicateResourceException if affiliation already exists
     * @throws InvalidOperationException if primaryAffiliation constraint is violated
     */
    AffiliatedWithEntity createAffiliation(Integer physicianId, AffiliatedWithEntity affiliation);

    /**
     * Get all affiliations for a given physician.
     * @throws ResourceNotFoundException if physician not found
     */
    List<AffiliatedWithEntity> getAffiliationsByPhysician(Integer physicianId);

    /**
     * Get all physicians affiliated with a given department.
     * @throws ResourceNotFoundException if department not found
     */
    List<AffiliatedWithEntity> getPhysiciansByDepartment(Integer departmentId);

}
