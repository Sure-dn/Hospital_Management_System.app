package com.sprint.project.EntityClasses;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Department")
public class Department {
	
	@Id
	@Column(name="DepartmentID")
	private Integer DepartmentID;
	
	@Column(name = "Name", nullable = false, length = 30)
    private String Name;
	
	@ManyToOne
	@JoinColumn(name = "Head", referencedColumnName = "EmployeeID")
	private Physician head;
	
	@OneToMany(mappedBy="department")
	private List<Affiliated_With> affiliations;
	
	//getters and setters

	public Integer getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		DepartmentID = departmentID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Physician getHead() {
		return head;
	}

	public void setHead(Physician head) {
		this.head = head;
	}

	public List<Affiliated_With> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Affiliated_With> affiliations) {
		this.affiliations = affiliations;
	}
	
	

	
	

}
