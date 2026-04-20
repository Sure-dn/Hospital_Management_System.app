package com.sprint.project.physicianDepartmentManagement.service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.AffiliatedWithRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responseDto.AffiliatedWithResponseDto;


public interface AffiliatedWithService {
	AffiliatedWithResponseDto createAffiliation(Integer physicianId, AffiliatedWithRequestDto requestDto);

    List<AffiliatedWithResponseDto> getAffiliationsByPhysician(Integer physicianId);

    List<AffiliatedWithResponseDto> getPhysiciansByDepartment(Integer departmentId);

}
