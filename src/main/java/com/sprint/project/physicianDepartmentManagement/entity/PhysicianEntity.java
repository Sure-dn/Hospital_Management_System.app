package com.sprint.project.physicianDepartmentManagement.entity;
import java.util.List;
import java.util.Objects;

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

		@Override
		public int hashCode() {
			return Objects.hash(affiliations, departments, employeeId, name, position, ssn);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PhysicianEntity other = (PhysicianEntity) obj;
			return Objects.equals(affiliations, other.affiliations) && Objects.equals(departments, other.departments)
					&& Objects.equals(employeeId, other.employeeId) && Objects.equals(name, other.name)
					&& Objects.equals(position, other.position) && Objects.equals(ssn, other.ssn);
		}

		@Override
		public String toString() {
			return "PhysicianEntity [employeeId=" + employeeId + ", name=" + name + ", position=" + position + ", ssn="
					+ ssn + ", departments=" + departments + ", affiliations=" + affiliations + "]";
		}
		
	    
	

}
