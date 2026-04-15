package com.sprint.project.EntityClasses;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AffiliatedId implements Serializable {
	
	 @NotNull
	 @Column(name = "Physician")
	 private Integer physician;
	 
	 @NotNull
	 @Column(name = "Department")
	 private Integer department;
	 
	 public AffiliatedId() {}
	 
	 public AffiliatedId(Integer physician, Integer department) {
	        this.physician = physician;
	        this.department = department;
	    }
	 
	 //getters and setters

	 public Integer getPhysician() {
		 return physician;
	 }

	 public void setPhysician(Integer physician) {
		 this.physician = physician;
	 }

	 public Integer getDepartment() {
		 return department;
	 }

	 public void setDepartment(Integer department) {
		 this.department = department;
	 }

	 @Override
	 public int hashCode() {
		return Objects.hash(department, physician);
	 }

	 @Override
	 public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffiliatedId other = (AffiliatedId) obj;
		return Objects.equals(department, other.department) && Objects.equals(physician, other.physician);
	 }
	 
	 
	 
	
	 
	 

	

}
