package com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto;

public class DepartmentResponseDto {
	 private Integer departmentId;
	    private String name;
	    private PhysicianResponseDto head;
	 
	    public DepartmentResponseDto() {}
	 
	    public DepartmentResponseDto(Integer departmentId, String name, PhysicianResponseDto head) {
	        this.departmentId = departmentId;
	        this.name = name;
	        this.head = head;
	    }
	 
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
	 
	    public PhysicianResponseDto getHead() {
	        return head;
	    }
	 
	    public void setHead(PhysicianResponseDto head) {
	        this.head = head;
	    }

}
