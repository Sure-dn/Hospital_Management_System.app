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

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

@Entity
@Table(name = "Prescribes")
@IdClass(PrescribesId.class)
public class PrescribesEntity {

    @Id
    @NotNull(message = "Physician is required")
    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID", nullable = false)
    private PhysicianEntity physician;

    @Id
    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private PatientEntity patient;

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
    private AppointmentEntity appointment;

    @NotBlank(message = "Dose cannot be empty")
    @Size(min = 1, max = 50, message = "Dose must be between 1 and 50 characters")
    @Column(name = "Dose", nullable = false, length = 50)
    private String dose;

    public PrescribesEntity() {}

    // Getters and Setters

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
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

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}

