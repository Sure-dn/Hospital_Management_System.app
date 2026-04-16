package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.PhysicianService;
import com.sprint.project.physicianDepartmentManagement.Exception.*;
@Service
public class PhysicianServiceImpl implements PhysicianService {
	 private final PhysicianRepository physicianRepository;

	    public PhysicianServiceImpl(PhysicianRepository physicianRepository) {
	        this.physicianRepository = physicianRepository;
	    }

	    @Override
	    public PhysicianEntity createPhysician(PhysicianEntity physician) {
	        // Business Rule: Physician ID must be unique
	        if (physicianRepository.existsById(physician.getEmployeeId())) {
	            throw new DuplicateResourceException("Physician", "EmployeeID", physician.getEmployeeId());
	        }
	        // Business Rule: SSN must be unique
	        boolean ssnExists = physicianRepository.findAll().stream()
	            .anyMatch(p -> p.getSsn().equals(physician.getSsn()));
	        if (ssnExists) {
	            throw new DuplicateResourceException("Physician", "SSN", physician.getSsn());
	        }
	        return physicianRepository.save(physician);
	    }

	    @Override
	    public List<PhysicianEntity> getAllPhysicians() {
	        return physicianRepository.findAll();
	    }

	    @Override
	    public PhysicianEntity getPhysicianById(Integer employeeId) {
	        return physicianRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Physician", "EmployeeID", employeeId));
	    }

	    @Override
	    public PhysicianEntity updatePhysician(Integer employeeId, PhysicianEntity updatedPhysician) {
	        PhysicianEntity existing = physicianRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Physician", "EmployeeID", employeeId));

	        // Business Rule: SSN uniqueness check (exclude current physician)
	        boolean ssnConflict = physicianRepository.findAll().stream()
	            .anyMatch(p -> p.getSsn().equals(updatedPhysician.getSsn())
	                       && !p.getEmployeeId().equals(employeeId));
	        if (ssnConflict) {
	            throw new DuplicateResourceException("Physician", "SSN", updatedPhysician.getSsn());
	        }

	        existing.setName(updatedPhysician.getName());
	        existing.setPosition(updatedPhysician.getPosition());
	        existing.setSsn(updatedPhysician.getSsn());
	        return physicianRepository.save(existing);
	    }

	    @Override
	    public void deletePhysician(Integer employeeId) {
	        PhysicianEntity physician = physicianRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Physician", "EmployeeID", employeeId));

	        // Business Rule: Cannot delete a physician who is head of any department
	        if (physician.getDepartments() != null && !physician.getDepartments().isEmpty()) {
	            throw new InvalidOperationException(
	                "Cannot delete Physician with EmployeeID " + employeeId
	                + " because they are the head of one or more departments."
	            );
	        }

	        physicianRepository.delete(physician);
	    }
	

}
