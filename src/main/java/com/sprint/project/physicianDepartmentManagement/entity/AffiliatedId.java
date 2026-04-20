package com.sprint.project.physicianDepartmentManagement.entity;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AffiliatedId {
	private static final long serialVersionUID = 1L;

    @Column(name = "Physician")
    private Integer physician;

    @Column(name = "Department")
    private Integer department;

    public AffiliatedId() {}

    public AffiliatedId(Integer physician, Integer department) {
        this.physician = physician;
        this.department = department;
    }

    // Getters and Setters

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AffiliatedId)) return false;
        AffiliatedId that = (AffiliatedId) o;
        return Objects.equals(physician, that.physician) &&
               Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physician, department);
    }

}
