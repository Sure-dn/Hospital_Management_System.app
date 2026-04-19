package com.sprint.project.physicianDepartmentManagement.Service.implementation;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.project.physicianDepartmentManagement.Dto.requestdto.AffiliatedWithRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.AffiliatedWithResponseDto;
import com.sprint.project.physicianDepartmentManagement.Service.AffiliatedWithService;
import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedWithEntity;
import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.exception.DepartmentNotFoundException;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateAffiliationException;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException;
import com.sprint.project.physicianDepartmentManagement.exception.PrimaryAffiliationException;
import com.sprint.project.physicianDepartmentManagement.repository.AffiliatedWithRepository;
import com.sprint.project.physicianDepartmentManagement.repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;

@Service
public class AffiliatedWithServiceImpl implements AffiliatedWithService {

    @Autowired
    private AffiliatedWithRepository affiliatedWithRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public AffiliatedWithServiceImpl(AffiliatedWithRepository affiliatedWithRepository,
                                     PhysicianRepository physicianRepository,
                                     DepartmentRepository departmentRepository) {
        this.affiliatedWithRepository = affiliatedWithRepository;
        this.physicianRepository = physicianRepository;
        this.departmentRepository = departmentRepository;
    }

    // CREATE AFFILIATION 
    @Override
    public AffiliatedWithResponseDto createAffiliation(Integer physicianId,
                                                        AffiliatedWithRequestDto requestDto) {

        PhysicianEntity physician = physicianRepository.findById(physicianId)
                .orElseThrow(() ->
                        new PhysicianNotFoundException("Physician not found: " + physicianId));

        DepartmentEntity department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found: " + requestDto.getDepartmentId()));

        AffiliatedId id = new AffiliatedId(physicianId, requestDto.getDepartmentId());

        if (affiliatedWithRepository.existsByAffiliatedId(id)) {
            throw new DuplicateAffiliationException(
                    "Affiliation already exists for Physician " + physicianId +
                    " and Department " + requestDto.getDepartmentId());
        }

        if (Boolean.TRUE.equals(requestDto.getPrimaryAffiliation())) {

            List<AffiliatedWithEntity> existing =
                    affiliatedWithRepository.findByPhysicianEmployeeId(physicianId);

            boolean alreadyPrimary = existing.stream()
                    .anyMatch(AffiliatedWithEntity::getPrimaryAffiliation);

            if (alreadyPrimary) {
                throw new PrimaryAffiliationException(
                        "Physician " + physicianId + " already has a primary affiliation");
            }
        }

        //  USING MAPPER (ENTITY CREATION)
        AffiliatedWithEntity entity = mapToEntity(id, physician, department, requestDto);

        AffiliatedWithEntity saved = affiliatedWithRepository.save(entity);

        return mapToResponse(saved);
    }

    //  GET BY PHYSICIAN 
    @Override
    public List<AffiliatedWithResponseDto> getAffiliationsByPhysician(Integer physicianId) {

        if (!physicianRepository.existsById(physicianId)) {
            throw new PhysicianNotFoundException("Physician not found: " + physicianId);
        }

        return affiliatedWithRepository.findByPhysicianEmployeeId(physicianId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY DEPARTMENT 
    @Override
    public List<AffiliatedWithResponseDto> getPhysiciansByDepartment(Integer departmentId) {

        if (!departmentRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException("Department not found: " + departmentId);
        }

        return affiliatedWithRepository.findByDepartmentDepartmentId(departmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //  MAP TO ENTITY 
    private AffiliatedWithEntity mapToEntity(AffiliatedId id,
                                            PhysicianEntity physician,
                                            DepartmentEntity department,
                                            AffiliatedWithRequestDto dto) {

        AffiliatedWithEntity entity = new AffiliatedWithEntity();
        entity.setAffiliatedId(id);
        entity.setPhysician(physician);
        entity.setDepartment(department);
        entity.setPrimaryAffiliation(dto.getPrimaryAffiliation());
        return entity;
    }

    //  MAP TO DTO 
    private AffiliatedWithResponseDto mapToResponse(AffiliatedWithEntity entity) {

        return new AffiliatedWithResponseDto(
                entity.getPhysician().getEmployeeId(),
                entity.getPhysician().getName(),
                entity.getDepartment().getDepartmentId(),
                entity.getDepartment().getName(),
                entity.getPrimaryAffiliation()
        );
    }
}