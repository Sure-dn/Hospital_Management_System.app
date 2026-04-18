package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.PhysicianResponseDto;




public interface DepartmentService {
	DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto getDepartmentById(Integer departmentId);

    DepartmentResponseDto updateDepartment(Integer departmentId, DepartmentRequestDto requestDto);

    PhysicianResponseDto getDepartmentHead(Integer departmentId);

}
