package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Dto.requestdto.AffiliatedWithRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.AffiliatedWithResponseDto;


public interface AffiliatedWithService {
	AffiliatedWithResponseDto createAffiliation(Integer physicianId, AffiliatedWithRequestDto requestDto);

    List<AffiliatedWithResponseDto> getAffiliationsByPhysician(Integer physicianId);

    List<AffiliatedWithResponseDto> getPhysiciansByDepartment(Integer departmentId);

}
