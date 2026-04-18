package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.physicianDepartmentManagement.Repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private  PhysicianRepository physicianRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 PhysicianRepository physicianRepository) {
        this.departmentRepository = departmentRepository;
        this.physicianRepository = physicianRepository;
    }

    // ✅ CREATE
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        if (departmentRepository.existsById(requestDto.getDepartmentId())) {
            throw new DuplicateResourceException("Department already exists" +requestDto.getDepartmentId());
        }

        PhysicianEntity head = physicianRepository.findById(requestDto.getHeadEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Physician is not found"+ requestDto.getHeadEmployeeId()));

        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentId(requestDto.getDepartmentId());
        department.setName(requestDto.getName());
        department.setHead(head);

        DepartmentEntity saved = departmentRepository.save(department);

        return mapToResponseDto(saved);
    }

    // ✅ GET ALL
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    @Override
    public DepartmentResponseDto getDepartmentById(Integer departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not found" + departmentId));

        return mapToResponseDto(department);
    }

    // ✅ UPDATE
    @Override
    public DepartmentResponseDto updateDepartment(Integer departmentId, DepartmentRequestDto requestDto) {

        DepartmentEntity existing = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not found"+ departmentId));

        existing.setName(requestDto.getName());

        PhysicianEntity head = physicianRepository.findById(requestDto.getHeadEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Physician is not found"+requestDto.getHeadEmployeeId()));

        existing.setHead(head);

        DepartmentEntity updated = departmentRepository.save(existing);

        return mapToResponseDto(updated);
    }

    // ✅ GET HEAD
    @Override
    public PhysicianResponseDto getDepartmentHead(Integer departmentId) {

        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not found"+departmentId));

        return mapToPhysicianDto(department.getHead());
    }

    // ===============================
    // 🔁 MAPPER METHODS (IMPORTANT)
    // ===============================

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
        dto.setName(entity.getName()); // adjust based on your fields
        return dto;
    }
}