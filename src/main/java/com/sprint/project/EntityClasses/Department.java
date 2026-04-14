package com.sprint.project.EntityClasses;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Department")
public class Department {
	@Id
	@NotNull
    @Column(name = "DepartmentID")
    private Integer DepartmentId;

	@NotBlank
    @Column(name = "Name")
    private String Name;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "Head")
    private Physician Head;
    
    @OneToMany(mappedBy = "department")
    private List<Affiliated_With> affiliations;
    
    //No Argument Constructor
    public Department() {
    	
    }
    
    //getters and setters

	public Integer getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		DepartmentId = departmentId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Physician getHead() {
		return Head;
	}

	public void setHead(Physician head) {
		Head = head;
	}

	public List<Affiliated_With> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Affiliated_With> affiliations) {
		this.affiliations = affiliations;
	}
    
    
    

}
