package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @Column(name = "AppointmentID")
    @NotNull
    private Integer appointmentId;

    // 🔗 Patient (FK → Patient.SSN)
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN")
    @NotNull
    private Patient patient;

    // 🔗 Nurse (FK → Nurse.EmployeeID)
    @ManyToOne
    @JoinColumn(name = "PrepNurse", referencedColumnName = "EmployeeID")
    private Nurse prepNurse;

    // 🔗 Physician (FK → Physician.EmployeeID)
    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID")
    @NotNull
    private Physician physician;

    @Column(name = "Starto", nullable = false)
    @NotNull
    private LocalDateTime start;

    @Column(name = "Endo", nullable = false)
    @NotNull
    private LocalDateTime end;

    @Column(name = "ExaminationRoom", nullable = false)
    @NotBlank
    private String examinationRoom;

    public Appointment() {}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Nurse getPrepNurse() {
		return prepNurse;
	}

	public void setPrepNurse(Nurse prepNurse) {
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