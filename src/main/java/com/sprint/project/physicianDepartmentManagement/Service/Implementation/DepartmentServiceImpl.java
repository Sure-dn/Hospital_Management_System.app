package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.exception.DepartmentNotFoundExcpetion;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateDepartmentException;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException;
import com.sprint.project.physicianDepartmentManagement.repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    
    // CREATE
    
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        if (departmentRepository.existsById(requestDto.getDepartmentId())) {
            throw new DuplicateDepartmentException(
                    "Department already exists: " + requestDto.getDepartmentId());
        }

        PhysicianEntity head = physicianRepository.findById(requestDto.getHeadEmployeeId())
                .orElseThrow(() -> new DepartmentNotFoundExcpetion(
                        "Physician not found: " + requestDto.getHeadEmployeeId()));

        DepartmentEntity department = mapToEntity(requestDto, head);

        DepartmentEntity saved = departmentRepository.save(department);

        return mapToResponseDto(saved);
    }

    
    // GET ALL
    
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    
    // GET BY ID
   
    @Override
    public DepartmentResponseDto getDepartmentById(Integer departmentId) {

        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new DepartmentNotFoundExcpetion("Department not found: " + departmentId));

        return mapToResponseDto(department);
    }

    
    // UPDATE
    
    @Override
    public DepartmentResponseDto updateDepartment(Integer departmentId, DepartmentRequestDto requestDto) {

        DepartmentEntity existing = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new DepartmentNotFoundExcpetion("Department not found: " + departmentId));

        PhysicianEntity head = physicianRepository.findById(requestDto.getHeadEmployeeId())
                .orElseThrow(() -> new PhysicianNotFoundException(
                        "Physician not found: " + requestDto.getHeadEmployeeId()));

        existing.setName(requestDto.getName());
        existing.setHead(head);

        DepartmentEntity updated = departmentRepository.save(existing);

        return mapToResponseDto(updated);
    }

    
    // GET HEAD
    
    @Override
    public PhysicianResponseDto getDepartmentHead(Integer departmentId) {

        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new DepartmentNotFoundExcpetion("Department not found: " + departmentId));

        return mapToPhysicianDto(department.getHead());
    }

    
    //  MAPPER METHODS (Helper Mehods)
    

    private DepartmentEntity mapToEntity(DepartmentRequestDto dto, PhysicianEntity head) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setName(dto.getName());
        entity.setHead(head);
        return entity;
    }

    private DepartmentResponseDto mapToResponseDto(DepartmentEntity entity) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setName(entity.getName());
        dto.setHead(mapToPhysicianDto(entity.getHead()));
        return dto;
    }

    private PhysicianResponseDto mapToPhysicianDto(PhysicianEntity entity) {
        PhysicianResponseDto dto = new PhysicianResponseDto();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setName(entity.getName());
        dto.setPosition(entity.getPosition());
        dto.setSsn(entity.getSsn());
        return dto;
    }
}