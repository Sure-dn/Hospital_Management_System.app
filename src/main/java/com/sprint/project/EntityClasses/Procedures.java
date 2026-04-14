package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Procedures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procedureId;

    @NotBlank(message = "Procedure name cannot be empty")
    private String procedureName;

    @NotNull(message = "Cost is required")
    private Double cost;

    // Relationship with Patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Relationship with Doctor (TrainedIn)
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Trained_In doctor;

    // Getters and Setters

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Trained_In getDoctor() {
        return doctor;
    }

    public void setDoctor(Trained_In doctor) {
        this.doctor = doctor;
    }
}


