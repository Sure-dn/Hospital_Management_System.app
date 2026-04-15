package com.sprint.project.Treatment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Undergoes")
public class Undergoes {
	@EmbeddedId
	private UndergoesId undergoesId;
    @NotNull(message = "Patient cannot be null")
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private Patient patient;

    @NotNull(message = "Procedure cannot be null")
    @ManyToOne
    @JoinColumn(name = "Procedures", referencedColumnName = "Code", nullable = false)
    private Procedures procedures;

    @NotNull(message = "Stay cannot be null")
    @ManyToOne
    @JoinColumn(name = "Stay", referencedColumnName = "StayID", nullable = false)
    private Stay stay;

    @NotNull(message = "Date of procedure is required")
    @Column(name = "DateUndergoes", nullable = false)
    private LocalDateTime dateUndergoes;

    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID")
    private Physician physician;

    @ManyToOne
    @JoinColumn(name = "AssistingNurse", referencedColumnName = "EmployeeID")
    private Nurse assistingNurse;

    public Undergoes() {}

    public Undergoes(Patient patient, Procedures procedures, Stay stay,
                     LocalDateTime dateUndergoes, Physician physician,
                     Nurse assistingNurse) {
        this.patient = patient;
        this.procedures = procedures;
        this.stay = stay;
        this.dateUndergoes = dateUndergoes;
        this.physician = physician;
        this.assistingNurse = assistingNurse;
    }

    // Getters and Setters

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Procedures getProcedures() {
        return procedures;
    }

    public void setProcedures(Procedures procedures) {
        this.procedures = procedures;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public LocalDateTime getDateUndergoes() {
        return dateUndergoes;
    }

    public void setDateUndergoes(LocalDateTime dateUndergoes) {
        this.dateUndergoes = dateUndergoes;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public Nurse getAssistingNurse() {
        return assistingNurse;
    }

    public void setAssistingNurse(Nurse assistingNurse) {
        this.assistingNurse = assistingNurse;
    }
}

