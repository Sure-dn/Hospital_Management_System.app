package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Dto.requestdto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.PhysicianResponseDto;




public interface DepartmentService {
	DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto getDepartmentById(Integer departmentId);

    DepartmentResponseDto updateDepartment(Integer departmentId, DepartmentRequestDto requestDto);

    PhysicianResponseDto getDepartmentHead(Integer departmentId);

}
