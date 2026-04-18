package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.AffiliatedWithRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.AffiliatedWithResponseDto;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.AffiliatedWithRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.AffiliatedWithService;

@Service
public class AffiliatedWithServiceImpl implements AffiliatedWithService {
    @Autowired
    private AffiliatedWithRepository affiliatedWithRepository;
    
    @Autowired
    private  PhysicianRepository physicianRepository;
    
    @Autowired
    private final DepartmentRepository departmentRepository;

    public AffiliatedWithServiceImpl(AffiliatedWithRepository affiliatedWithRepository,
                                     PhysicianRepository physicianRepository,
                                     DepartmentRepository departmentRepository) {
        this.affiliatedWithRepository = affiliatedWithRepository;
        this.physicianRepository = physicianRepository;
        this.departmentRepository = departmentRepository;
    }

    // ================= CREATE AFFILIATION =================
    @Override
    public AffiliatedWithResponseDto createAffiliation(Integer physicianId,
                                                        AffiliatedWithRequestDto requestDto) {

        // 1. Validate physician
        PhysicianEntity physician = physicianRepository.findById(physicianId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(  "Physician not found with EmployeeID: " + physicianId));

        // 2. Validate department
        DepartmentEntity department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException( "Department not found with ID: " + requestDto.getDepartmentId()));

        // 3. Create composite key
        AffiliatedId id = new AffiliatedId(physicianId, requestDto.getDepartmentId());

        // 4. Check duplicate affiliation
        if (affiliatedWithRepository.existsByAffiliatedId(id)) {
            throw new DuplicateResourceException( "Affiliation already exists for Physician " + physicianId +
                    " and Department " + requestDto.getDepartmentId()
                    );
        }

        // 5. Business rule: only ONE primary affiliation per physician
        if (Boolean.TRUE.equals(requestDto.getPrimaryAffiliation())) {

            List<AffiliatedWithEntity> existing =
                    affiliatedWithRepository.findByPhysicianEmployeeId(physicianId);

            boolean alreadyPrimary = existing.stream()
                    .anyMatch(AffiliatedWithEntity::getPrimaryAffiliation);

            if (alreadyPrimary) {
                throw new InvalidDnDOperationException(
                        "Physician " + physicianId +
                        " already has a primary affiliation");
            }
        }

        // 6. Create entity
        AffiliatedWithEntity entity = new AffiliatedWithEntity();
        entity.setAffiliatedId(id);
        entity.setPhysician(physician);
        entity.setDepartment(department);
        entity.setPrimaryAffiliation(requestDto.getPrimaryAffiliation());

        // 7. Save
        AffiliatedWithEntity saved = affiliatedWithRepository.save(entity);

        return mapToResponse(saved);
    }

    // ================= GET BY PHYSICIAN =================
    @Override
    public List<AffiliatedWithResponseDto> getAffiliationsByPhysician(Integer physicianId) {

        if (!physicianRepository.existsById(physicianId)) {
        	throw new ResourceNotFoundException(
        		    "Physician not found with EmployeeID: " + physicianId
        		);
        }

        return affiliatedWithRepository.findByPhysicianEmployeeId(physicianId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ================= GET BY DEPARTMENT =================
    @Override
    public List<AffiliatedWithResponseDto> getPhysiciansByDepartment(Integer departmentId) {

        if (!departmentRepository.existsById(departmentId)) {
        	throw new ResourceNotFoundException(
        		    "Department not found with DepartmentID: " + departmentId
        		);
        }

        return affiliatedWithRepository.findByDepartmentDepartmentId(departmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ================= MAPPER =================
    private AffiliatedWithResponseDto mapToResponse(AffiliatedWithEntity entity) {

        return new AffiliatedWithResponseDto(
                entity.getPhysician().getEmployeeId(),
                entity.getPhysician().getName(), // adjust if field name differs
                entity.getDepartment().getDepartmentId(),
                entity.getDepartment().getName(), // adjust if needed
                entity.getPrimaryAffiliation()
        );
    }
}