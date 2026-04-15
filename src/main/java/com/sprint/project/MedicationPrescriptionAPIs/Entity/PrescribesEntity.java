package com.sprint.project.MedicationPrescriptionAPIs.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "Prescribes")
@IdClass(PrescribesId.class)
public class PrescribesEntity {

    @Id
    @NotNull(message = "Physician is required")
    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID", nullable = false)
    private Physician physician;

    @Id
    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private Patient patient;

    @Id
    @NotNull(message = "Medication is required")
    @ManyToOne
    @JoinColumn(name = "Medication", referencedColumnName = "Code", nullable = false)
    private MedicationEntity medication;

    @Id
    @NotNull(message = "Date is required")
    @Column(name = "Date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Appointment", referencedColumnName = "AppointmentID")
    private Appointment appointment;

    @NotBlank(message = "Dose cannot be empty")
    @Size(min = 1, max = 50, message = "Dose must be between 1 and 50 characters")
    @Column(name = "Dose", nullable = false, length = 50)
    private String dose;

    public PrescribesEntity() {}

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

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
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

