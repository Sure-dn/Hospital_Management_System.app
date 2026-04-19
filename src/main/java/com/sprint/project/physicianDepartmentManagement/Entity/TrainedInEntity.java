package com.sprint.project.physicianDepartmentManagement.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import com.sprint.project.treatmentProStay.Entity.ProceduresEntity;
@Entity
@Table(name = "Trained_In")
public class TrainedInEntity {
	    @EmbeddedId
	    private TrainedInId trainedInId;

	    @NotNull(message = "Physician is required")
	    @ManyToOne
	    @MapsId("physician")
	    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID", nullable = false)
	    private PhysicianEntity physician;

	    @NotNull(message = "Treatment is required")
	    @ManyToOne
	    @MapsId("treatment")
	    @JoinColumn(name = "Treatment", referencedColumnName = "Code", nullable = false)
	    private ProceduresEntity treatment;

	    @NotNull(message = "Certification expiry date is required")
	    @Column(name = "CertificationExpiry", nullable = false)
	    private LocalDate certificationExpiry;

	    public TrainedInEntity() {}

	    public TrainedInEntity(TrainedInId trainedInId, PhysicianEntity physician, ProceduresEntity treatment, LocalDate certificationExpiry) {
	        this.trainedInId = trainedInId;
	        this.physician = physician;
	        this.treatment = treatment;
	        this.certificationExpiry = certificationExpiry;
	    }

	    // Getters and Setters

	    public TrainedInId getTrainedInId() {
	        return trainedInId;
	    }

	    public void setTrainedInId(TrainedInId trainedInId) {
	        this.trainedInId = trainedInId;
	    }

	    public PhysicianEntity getPhysician() {
	        return physician;
	    }

	    public void setPhysician(PhysicianEntity physician) {
	        this.physician = physician;
	    }

	    public ProceduresEntity getTreatment() {
	        return treatment;
	    }

	    public void setTreatment(ProceduresEntity treatment) {
	        this.treatment = treatment;
	    }

	    public LocalDate getCertificationExpiry() {
	        return certificationExpiry;
	    }

	    public void setCertificationExpiry(LocalDate certificationExpiry) {
	        this.certificationExpiry = certificationExpiry;
	    }

		@Override
		public String toString() {
			return "TrainedInEntity [trainedInId=" + trainedInId + ", physician=" + physician + ", treatment="
					+ treatment + ", certificationExpiry=" + certificationExpiry + "]";
		}
		
		
	    
	

}
