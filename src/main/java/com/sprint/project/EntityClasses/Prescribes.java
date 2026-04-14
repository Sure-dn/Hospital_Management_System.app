package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.sprint.project.EntityClasses.Patient;

@Entity
@IdClass(PrescribesId.class)
public class Prescribes {

    @Id
    @ManyToOne
    @JoinColumn(name = "Physician")
    private Physician physician;

    @Id
    @ManyToOne
    @JoinColumn(name = "Patient")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "Medication")
    private Medication medication;

    @Id
    @Column(name = "Date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Appointment")
    private Appointment appointment;

    @Column(name = "Dose", nullable = false)
    private String dose;

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

    
}
