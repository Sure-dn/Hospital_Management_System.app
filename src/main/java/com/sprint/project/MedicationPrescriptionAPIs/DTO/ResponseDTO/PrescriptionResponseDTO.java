package com.sprint.project.MedicationPrescriptionAPIs.DTO.ResponseDTO;

import java.time.LocalDateTime;

public class PrescriptionResponseDTO {
    private Integer physicianId;
    private String physicianName;
    private Integer patientSsn;
    private String patientName;
    private Integer medicationCode;
    private String medicationName;
    private LocalDateTime date;
    private Integer appointmentId;
    private String dose;

    // constructors
    public PrescriptionResponseDTO() {}
    
    
    
    public PrescriptionResponseDTO(Integer physicianId, String physicianName, Integer patientSsn, String patientName,
			Integer medicationCode, String medicationName, LocalDateTime date, Integer appointmentId, String dose) {
		super();
		this.physicianId = physicianId;
		this.physicianName = physicianName;
		this.patientSsn = patientSsn;
		this.patientName = patientName;
		this.medicationCode = medicationCode;
		this.medicationName = medicationName;
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

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public Integer getPatientSsn() {
		return patientSsn;
	}

	public void setPatientSsn(Integer patientSsn) {
		this.patientSsn = patientSsn;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getMedicationCode() {
		return medicationCode;
	}

	public void setMedicationCode(Integer medicationCode) {
		this.medicationCode = medicationCode;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
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