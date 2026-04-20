package com.sprint.project.physicianDepartmentManagement.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PhysicianRequestDto {
	 @NotNull(message = "Employee ID is required")
	    private Integer employeeId;
	 
	    @NotBlank(message = "Name is required")
	    @Size(max = 30, message = "Name must not exceed 30 characters")
	    private String name;
	 
	    @NotBlank(message = "Position is required")
	    @Size(max = 30, message = "Position must not exceed 30 characters")
	    private String position;
	 
	    @NotNull(message = "SSN is required")
	    private Integer ssn;
	 
	    // Getters and Setters
	 
	    public Integer getEmployeeId() {
	        return employeeId;
	    }
	 
	    public void setEmployeeId(Integer employeeId) {
	        this.employeeId = employeeId;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	    public String getPosition() {
	        return position;
	    }
	 
	    public void setPosition(String position) {
	        this.position = position;
	    }
	 
	    public Integer getSsn() {
	        return ssn;
	    }
	 
	    public void setSsn(Integer ssn) {
	        this.ssn = ssn;
	    }

}
