package com.sprint.project.physicianDepartmentManagement.Dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentRequestDto {
	 @NotNull(message = "Department ID is required")
	    private Integer departmentId;
	 
	    @NotBlank(message = "Department name is required")
	    @Size(max = 100, message = "Department name must not exceed 100 characters")
	    private String name;
	 
	    @NotNull(message = "Head physician Employee ID is required")
	    private Integer headEmployeeId;
	 
	    // Getters and Setters
	 
	    public Integer getDepartmentId() {
	        return departmentId;
	    }
	 
	    public void setDepartmentId(Integer departmentId) {
	        this.departmentId = departmentId;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	    public Integer getHeadEmployeeId() {
	        return headEmployeeId;
	    }
	 
	    public void setHeadEmployeeId(Integer headEmployeeId) {
	        this.headEmployeeId = headEmployeeId;
	    }

}
