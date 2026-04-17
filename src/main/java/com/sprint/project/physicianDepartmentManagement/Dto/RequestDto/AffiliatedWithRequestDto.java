package com.sprint.project.physicianDepartmentManagement.Dto.RequestDto;

import jakarta.validation.constraints.NotNull;

public class AffiliatedWithRequestDto {
	@NotNull(message = "Department ID is required")
    private Integer departmentId;
 
    @NotNull(message = "Primary affiliation flag is required")
    private Boolean primaryAffiliation;
 
    // Getters and Setters
 
    public Integer getDepartmentId() {
        return departmentId;
    }
 
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
 
    public Boolean getPrimaryAffiliation() {
        return primaryAffiliation;
    }
 
    public void setPrimaryAffiliation(Boolean primaryAffiliation) {
        this.primaryAffiliation = primaryAffiliation;
    }
}
