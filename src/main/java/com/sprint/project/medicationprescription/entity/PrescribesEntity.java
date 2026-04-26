package com.sprint.project.medicationprescription.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.sprint.project.patientAppointment.entity.AppointmentEntity;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;

@Entity
@Table(name = "Prescribes")
@IdClass(PrescribesId.class)
public class PrescribesEntity {

    // ===== PHYSICIAN =====
    @Id
    @ManyToOne
    @MapsId("physician") // 🔥 important
    @JoinColumn(name = "Physician", referencedColumnName = "employee_id", nullable = false)
    private PhysicianEntity physician;

    // ===== PATIENT =====
    @Id
    @ManyToOne
    @MapsId("patient") // 🔥 important
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private PatientEntity patient;

    // ===== MEDICATION =====
    @Id
    @ManyToOne
    @MapsId("medication") // 🔥 important
    @JoinColumn(name = "Medication", referencedColumnName = "Code", nullable = false)
    private MedicationEntity medication;

    // ===== DATE =====
    @Id
    @NotNull(message = "Date is required")
    @MapsId("date") // 🔥 important
    @Column(name = "Date", nullable = false)
    private LocalDateTime date;

    // ===== APPOINTMENT (OPTIONAL) =====
    @ManyToOne
    @JoinColumn(name = "Appointment", referencedColumnName = "AppointmentID")
    private AppointmentEntity appointment;

    // ===== DOSE =====
    @NotBlank(message = "Dose cannot be empty")
    @Size(min = 1, max = 50, message = "Dose must be between 1 and 50 characters")
    @Column(name = "Dose", nullable = false, length = 50)
    private String dose;

    public PrescribesEntity() {}

    // ===== GETTERS & SETTERS =====

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

    // ===== DEBUG =====
    @Override
    public String toString() {
        return "PrescribesEntity{" +
                "physician=" + (physician != null ? physician.getEmployeeId() : null) +
                ", patient=" + (patient != null ? patient.getSsn() : null) +
                ", medication=" + (medication != null ? medication.getCode() : null) +
                ", date=" + date +
                ", appointment=" + (appointment != null ? appointment.getAppointmentId() : null) +
                ", dose='" + dose + '\'' +
                '}';
    }
}