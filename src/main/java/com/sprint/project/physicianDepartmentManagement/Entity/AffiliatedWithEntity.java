package com.sprint.project.physicianDepartmentManagement.Entity;

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
public class AffiliatedWithEntity {
	@EmbeddedId
    private AffiliatedId affiliatedId;

    @NotNull
    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID", nullable = false)
    private PhysicianEntity physician;

    @NotNull
    @ManyToOne
    @MapsId("department")
    @JoinColumn(name = "Department", referencedColumnName = "DepartmentID", nullable = false)
    private DepartmentEntity department;

    @NotNull
    @Column(name = "PrimaryAffiliation", nullable = false)
    private Boolean primaryAffiliation;

    public AffiliatedWithEntity() {}

    // Getters and Setters

    public AffiliatedId getAffiliatedId() {
        return affiliatedId;
    }

    public void setAffiliatedId(AffiliatedId affiliatedId) {
        this.affiliatedId = affiliatedId;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public Boolean getPrimaryAffiliation() {
        return primaryAffiliation;
    }

    public void setPrimaryAffiliation(Boolean primaryAffiliation) {
        this.primaryAffiliation = primaryAffiliation;
    }

}
