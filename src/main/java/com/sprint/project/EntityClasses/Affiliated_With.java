package com.sprint.project.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Affiliated_With")
public class Affiliated_With {
	@EmbeddedId
    private AffiliatedId affiliatedid;
	
    @NotNull
    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name = "Physician", nullable=false)
    private Physician physician;
    
    @NotNull
    @ManyToOne
    @MapsId("department")
    @JoinColumn(name = "Department",nullable=false)
    private Department department;
    
    @NotNull
    @Column(name = "PrimaryAffiliation", nullable = false)
    private Boolean primaryAffiliation;

    public Affiliated_With() {
    	
    }
    
    //getters and setters

	

	public Physician getPhysician() {
		return physician;
	}

	public AffiliatedId getAffiliatedid() {
		return affiliatedid;
	}

	public void setAffiliatedid(AffiliatedId affiliatedid) {
		this.affiliatedid = affiliatedid;
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
