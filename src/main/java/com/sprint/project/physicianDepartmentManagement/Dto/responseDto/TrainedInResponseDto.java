package com.sprint.project.physicianDepartmentManagement.Dto.responseDto;

import java.time.LocalDate;

public class TrainedInResponseDto {
	  private Integer physicianId;
	    private String physicianName;
	    private Integer treatmentCode;
	    private String treatmentName;
	    private LocalDate certificationExpiry;
	    /** Derived field: true when certificationExpiry is after today */
	    private Boolean valid;
	 
	    public TrainedInResponseDto() {}
	 
	    public TrainedInResponseDto(Integer physicianId, String physicianName,
	                                Integer treatmentCode, String treatmentName,
	                                LocalDate certificationExpiry, Boolean valid) {
	        this.physicianId = physicianId;
	        this.physicianName = physicianName;
	        this.treatmentCode = treatmentCode;
	        this.treatmentName = treatmentName;
	        this.certificationExpiry = certificationExpiry;
	        this.valid = valid;
	    }
	 
	    // Getters and Setters
	 
	    public Integer getPhysicianId() {
	        return physicianId;
	    }
	 
	    public void setPhysicianId(Integer physicianId) {
	        this.physicianId = physicianId;
	    }
	 
	    public String getPhysicianName() {
	        return physicianName;
	    }
	 
	    public void setPhysicianName(String physicianName) {
	        this.physicianName = physicianName;
	    }
	 
	    public Integer getTreatmentCode() {
	        return treatmentCode;
	    }
	 
	    public void setTreatmentCode(Integer treatmentCode) {
	        this.treatmentCode = treatmentCode;
	    }
	 
	    public String getTreatmentName() {
	        return treatmentName;
	    }
	 
	    public void setTreatmentName(String treatmentName) {
	        this.treatmentName = treatmentName;
	    }
	 
	    public LocalDate getCertificationExpiry() {
	        return certificationExpiry;
	    }
	 
	    public void setCertificationExpiry(LocalDate certificationExpiry) {
	        this.certificationExpiry = certificationExpiry;
	    }
	 
	    public Boolean getValid() {
	        return valid;
	    }
	 
	    public void setValid(Boolean valid) {
	        this.valid = valid;
	    }

}
