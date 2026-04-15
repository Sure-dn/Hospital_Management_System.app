package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@IdClass(PrescribesId.class)
public class Prescribes {

    @Id
    @ManyToOne
    @JoinColumn(name = "Physician")
    @NotNull(message = "Physician cannot be null")
    private Physician physician;

    @Id
    @ManyToOne
    @JoinColumn(name = "Patient")
    @NotNull(message = "Patient cannot be null")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "Medication")
    @NotNull(message = "Medication cannot be null")
    private Medication medication;

    @Id
    @Column(name = "Date")
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Appointment")
    private Appointment appointment;

    @Column(name = "Dose", nullable = false)
    @NotBlank(message = "Dose cannot be empty")
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