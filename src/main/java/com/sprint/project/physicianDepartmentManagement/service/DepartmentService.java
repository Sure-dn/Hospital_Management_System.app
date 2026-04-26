package com.sprint.project.physicianDepartmentManagement.service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.PhysicianResponseDto;




public interface DepartmentService {
	DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto getDepartmentById(Integer departmentId);

    DepartmentResponseDto updateDepartment(Integer departmentId, DepartmentRequestDto requestDto);

    PhysicianResponseDto getDepartmentHead(Integer departmentId);

}
