package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@IdClass(PrescribesId.class)
public class Prescribes {

    @Id
    @ManyToOne
    @JoinColumn(name = "Physician")
    @NotNull(message = "Physician is required")
    private Physician physician;

    @Id
    @ManyToOne
    @JoinColumn(name = "Patient")
    @NotNull(message = "Patient is required")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "Medication")
    @NotNull(message = "Medication is required")
    private Medication medication;

    @Id
    @Column(name = "Date")
    @NotNull(message = "Date is required")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Appointment")
    private Appointment appointment;

    @Column(name = "Dose", nullable = false)
    @NotBlank(message = "Dose cannot be empty")
    @Size(min = 1, max = 50, message = "Dose must be between 1 and 50 characters")
    private String dose;

    // Getters and Setters

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