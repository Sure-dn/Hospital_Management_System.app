package com.sprint.project.EntityClasses;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Physician")
public class Physician {
	
	@Id
	@NotNull
    @Column(name = "EmployeeID")
    private Integer EmployeeId;

	@NotBlank
    @Column(name = "Name", length = 30)
    private String Name;
	
    @NotBlank
    @Column(name = "Position", length = 30)
    private String Position;
    
    @NotNull
    @Column(name = "SSN")
    private Integer SSN;
    
    @OneToMany(mappedBy = "head")
    private List<Department> departments;
    
    @OneToMany(mappedBy = "physician")
    private List<Affiliated_With> affiliations;
    
    //No argument constructor
    
    public Physician() {
    	
    }
    
    //getters and setters

	public Integer getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		EmployeeId = employeeId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public Integer getSSN() {
		return SSN;
	}

	public void setSSN(Integer sSN) {
		SSN = sSN;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Affiliated_With> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Affiliated_With> affiliations) {
		this.affiliations = affiliations;
	}
    
    
    
    

	

}
