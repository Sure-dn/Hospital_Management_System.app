package com.sprint.project.medicationprescription.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PrescriptionRequestDTO {
    @NotNull(message = "Physician ID is required") private Integer physicianId;
    @NotNull(message = "Patient SSN is required") private Integer patientSsn;
    @NotNull(message = "Medication Code is required") private Integer medicationCode;
    @NotNull(message = "Date is required") private LocalDateTime date;
    private Integer appointmentId; // optional
    @NotBlank(message = "Dose cannot be empty") private String dose;

    // constructors
    public PrescriptionRequestDTO() {}
    
    
    public PrescriptionRequestDTO(@NotNull(message = "Physician ID is required") Integer physicianId,
			@NotNull(message = "Patient SSN is required") Integer patientSsn,
			@NotNull(message = "Medication Code is required") Integer medicationCode,
			@NotNull(message = "Date is required") LocalDateTime date, Integer appointmentId,
			@NotBlank(message = "Dose cannot be empty") String dose) {
		super();
		this.physicianId = physicianId;
		this.patientSsn = patientSsn;
		this.medicationCode = medicationCode;
		this.date = date;
		this.appointmentId = appointmentId;
		this.dose = dose;
	}



	// getters and setters
    
	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}

	public Integer getPatientSsn() {
		return patientSsn;
	}

	public void setPatientSsn(Integer patientSsn) {
		this.patientSsn = patientSsn;
	}

	public Integer getMedicationCode() {
		return medicationCode;
	}

	public void setMedicationCode(Integer medicationCode) {
		this.medicationCode = medicationCode;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}
    
   
    
    
    
}