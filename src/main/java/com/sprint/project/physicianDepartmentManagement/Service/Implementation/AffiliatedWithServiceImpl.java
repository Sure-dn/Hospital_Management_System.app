package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Exception.DuplicateResourceException;
import com.sprint.project.physicianDepartmentManagement.Exception.InvalidOperationException;
import com.sprint.project.physicianDepartmentManagement.Exception.ResourceNotFoundException;
import com.sprint.project.physicianDepartmentManagement.Repository.AffiliatedWithRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.AffiliatedWithService;

@Service
public class AffiliatedWithServiceImpl implements AffiliatedWithService {

    private final AffiliatedWithRepository affiliatedWithRepository;
    private final PhysicianRepository physicianRepository;
    private final DepartmentRepository departmentRepository;

    public AffiliatedWithServiceImpl(AffiliatedWithRepository affiliatedWithRepository,
                                      PhysicianRepository physicianRepository,
                                      DepartmentRepository departmentRepository) {
        this.affiliatedWithRepository = affiliatedWithRepository;
        this.physicianRepository = physicianRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public AffiliatedWithEntity createAffiliation(Integer physicianId, AffiliatedWithEntity affiliation) {
        // Validate physician exists
        PhysicianEntity physician = physicianRepository.findById(physicianId)
            .orElseThrow(() -> new ResourceNotFoundException("Physician", "EmployeeID", physicianId));

        Integer departmentId = affiliation.getDepartment().getDepartmentId();
        DepartmentEntity department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentID", departmentId));

        AffiliatedId affiliatedId = new AffiliatedId(physicianId, departmentId);

        // Business Rule: No duplicate affiliations
        if (affiliatedWithRepository.existsByAffiliatedId(affiliatedId)) {
            throw new DuplicateResourceException("Affiliation",
                "PhysicianID + DepartmentID", physicianId + "+" + departmentId);
        }

        // Business Rule: A physician can only have ONE primary affiliation
        if (Boolean.TRUE.equals(affiliation.getPrimaryAffiliation())) {
            List<AffiliatedWithEntity> existing = affiliatedWithRepository.findByPhysician_PhysicianId(physicianId);
            boolean hasPrimary = existing.stream()
                .anyMatch(a -> Boolean.TRUE.equals(a.getPrimaryAffiliation()));
            if (hasPrimary) {
                throw new InvalidOperationException(
                    "Physician with EmployeeID " + physicianId
                    + " already has a primary affiliation. Only one primary affiliation is allowed."
                );
            }
        }

        affiliation.setAffiliatedId(affiliatedId);
        affiliation.setPhysician(physician);
        affiliation.setDepartment(department);
        return affiliatedWithRepository.save(affiliation);
    }

    @Override
    public List<AffiliatedWithEntity> getAffiliationsByPhysician(Integer physicianId) {
        if (!physicianRepository.existsById(physicianId)) {
            throw new ResourceNotFoundException("Physician", "EmployeeID", physicianId);
        }
        return affiliatedWithRepository.findByPhysician_PhysicianId(physicianId);
    }

    @Override
    public List<AffiliatedWithEntity> getPhysiciansByDepartment(Integer departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department", "DepartmentID", departmentId);
        }
        return affiliatedWithRepository.findByDepartment_DepartmentId(departmentId);
    }
}
