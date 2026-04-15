
	package com.sprint.project.patientAppointment.Entity;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;

	import java.time.LocalDateTime;

	@Entity
	@Table(name = "Appointment")
	public class AppointmentEntity {

	    @Id
	    @NotNull
	    @Column(name = "AppointmentID")
	    private Integer appointmentId;

	    @NotNull
	    @ManyToOne
	    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
	    private PatientEntity patient;

	    @ManyToOne
	    @JoinColumn(name = "PrepNurse", referencedColumnName = "EmployeeID")
	    private NurseEntity prepNurse;

	    @NotNull
	    @ManyToOne
	    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID", nullable = false)
	    private Physician physician;

	    @NotNull
	    @Column(name = "Start", nullable = false)
	    private LocalDateTime start;

	    @NotNull
	    @Column(name = "End", nullable = false)
	    private LocalDateTime end;

	    @NotBlank
	    @Column(name = "ExaminationRoom", nullable = false, length = 10)
	    private String examinationRoom;

	    public AppointmentEntity() {}

	    // Getters and Setters

	    public Integer getAppointmentId() {
	        return appointmentId;
	    }

	    public void setAppointmentId(Integer appointmentId) {
	        this.appointmentId = appointmentId;
	    }

	    public PatientEntity getPatient() {
	        return patient;
	    }

	    public void setPatient(PatientEntity patient) {
	        this.patient = patient;
	    }

	    public NurseEntity getPrepNurse() {
	        return prepNurse;
	    }

	    public void setPrepNurse(NurseEntity prepNurse) {
	        this.prepNurse = prepNurse;
	    }

	    public Physician getPhysician() {
	        return physician;
	    }

	    public void setPhysician(Physician physician) {
	        this.physician = physician;
	    }

	    public LocalDateTime getStart() {
	        return start;
	    }

	    public void setStart(LocalDateTime start) {
	        this.start = start;
	    }

	    public LocalDateTime getEnd() {
	        return end;
	    }

	    public void setEnd(LocalDateTime end) {
	        this.end = end;
	    }

	    public String getExaminationRoom() {
	        return examinationRoom;
	    }

	    public void setExaminationRoom(String examinationRoom) {
	        this.examinationRoom = examinationRoom;
	    }
	}



