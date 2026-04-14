package com.sprint.project.EntityClasses;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Physician")
public class Physician {
	
	@Id
	@Column(name="EmployeeID")
	private Integer EmployeeId;
	
	@Column(name="Name",nullable=false,length=30)
	private String Name;
	
	@Column(name="Position",nullable=false,length=30)
	private String Position;
	
    @Column(name = "SSN", nullable = false)
	private Integer SSN;
    
    @OneToMany(mappedBy="head")
    private List<Department> departments;
    
    @OneToMany(mappedBy="physician")
    private List<Affiliated_With> affiliations;
    
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
