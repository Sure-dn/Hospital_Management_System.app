package com.sprint.project.physicianDepartmentManagement.Dto.requestdto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class TrainedInRequestDto {
	
	 @NotNull(message = "Physician Id is required")
	    private Integer physicianId;
	 
	@NotNull(message = "Treatment (procedure) code is required")
    private Integer treatmentCode;
 
    @NotNull(message = "Certification expiry date is required")
    @Future(message = "Certification expiry date must be a future date")
    private LocalDate certificationExpiry;
 
    // Getters and Setters

    public Integer getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(Integer physicianId) {
        this.physicianId = physicianId;
    }
 
    public Integer getTreatmentCode() {
        return treatmentCode;
    }
 
    public void setTreatmentCode(Integer treatmentCode) {
        this.treatmentCode = treatmentCode;
    }
 
    public LocalDate getCertificationExpiry() {
        return certificationExpiry;
    }
 
    public void setCertificationExpiry(LocalDate certificationExpiry) {
        this.certificationExpiry = certificationExpiry;
    }

	
	

}
