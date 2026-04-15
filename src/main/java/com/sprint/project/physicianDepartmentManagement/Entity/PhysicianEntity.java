package com.sprint.project.physicianDepartmentManagement.Entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Physician")
public class PhysicianEntity {
	 @Id
	    @NotNull
	    @Column(name = "EmployeeID")
	    private Integer employeeId;

	    @NotBlank
	    @Column(name = "Name", length = 30, nullable = false)
	    private String name;

	    @NotBlank
	    @Column(name = "Position", length = 30, nullable = false)
	    private String position;

	    @NotNull
	    @Column(name = "SSN", unique = true, nullable = false)
	    private Integer ssn;

	    @OneToMany(mappedBy = "head")
	    private List<DepartmentEntity> departments;

	    @OneToMany(mappedBy = "physician")
	    private List<AffiliatedWithEntity> affiliations;

	    public PhysicianEntity() {}

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

	    public List<DepartmentEntity> getDepartments() {
	        return departments;
	    }

	    public void setDepartments(List<DepartmentEntity> departments) {
	        this.departments = departments;
	    }

	    public List<AffiliatedWithEntity> getAffiliations() {
	        return affiliations;
	    }

	    public void setAffiliations(List<AffiliatedWithEntity> affiliations) {
	        this.affiliations = affiliations;
	    }
	

}
