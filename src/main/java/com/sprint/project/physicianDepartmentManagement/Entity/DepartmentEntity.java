package com.sprint.project.physicianDepartmentManagement.Entity;
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
public class DepartmentEntity {
	 @Id
	    @NotNull
	    @Column(name = "DepartmentID")
	    private Integer departmentId;

	    @NotBlank
	    @Column(name = "Name", nullable = false, length = 100)
	    private String name;

	    @NotNull
	    @ManyToOne
	    @JoinColumn(name = "Head", referencedColumnName = "EmployeeID", nullable = false)
	    private PhysicianEntity head;

	    @OneToMany(mappedBy = "department")
	    private List<AffiliatedWithEntity> affiliations;

	    public DepartmentEntity() {}

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

	    public PhysicianEntity getHead() {
	        return head;
	    }

	    public void setHead(PhysicianEntity head) {
	        this.head = head;
	    }

	    public List<AffiliatedWithEntity> getAffiliations() {
	        return affiliations;
	    }

	    public void setAffiliations(List<AffiliatedWithEntity> affiliations) {
	        this.affiliations = affiliations;
	    }
	    

}
