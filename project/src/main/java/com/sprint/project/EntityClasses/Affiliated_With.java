package com.sprint.project.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Affiliated_With")
public class Affiliated_With {
	
	@Id
	@ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID")
	private Physician physician;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "Department", referencedColumnName = "DepartmentID")
	private Department department;
	
	@Column(name = "PrimaryAffiliation", nullable = false)
    private Boolean primaryAffiliation;
	
	//getters and setters

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Boolean getPrimaryAffiliation() {
		return primaryAffiliation;
	}

	public void setPrimaryAffiliation(Boolean primaryAffiliation) {
		this.primaryAffiliation = primaryAffiliation;
	}
	
	

	
	

}
