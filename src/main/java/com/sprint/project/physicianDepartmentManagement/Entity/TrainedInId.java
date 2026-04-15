package com.sprint.project.physicianDepartmentManagement.Entity;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TrainedInId implements Serializable {
	private Integer physician;
    private Integer treatment;

    // Default Constructor
    public TrainedInId() {}

    // Parameterized Constructor
    public TrainedInId(Integer physician, Integer treatment) {
        this.physician = physician;
        this.treatment = treatment;
    }

    // Getters and Setters
    public Integer getPhysician() {
        return physician;
    }

    public void setPhysician(Integer physician) {
        this.physician = physician;
    }

    public Integer getTreatment() {
        return treatment;
    }

    public void setTreatment(Integer treatment) {
        this.treatment = treatment;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainedInId that = (TrainedInId) o;
        return Objects.equals(physician, that.physician) &&
               Objects.equals(treatment, that.treatment);
    }


    @Override
    public int hashCode() {
        return Objects.hash(physician, treatment);
    }

}
