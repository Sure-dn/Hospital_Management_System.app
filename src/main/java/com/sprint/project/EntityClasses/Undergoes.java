package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(UndergoesId.class)
@Table(name = "Undergoes")
public class Undergoes {

    @Id
    @ManyToOne
    @JoinColumn(name = "Patient")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "Procedures")
    private Procedures procedures;

    @Id
    @ManyToOne
    @JoinColumn(name = "Stay")
    private Stay stay;

    @Id
    @Column(name = "DateUndergoes")
    private LocalDateTime dateUndergoes;

    @ManyToOne
    @JoinColumn(name = "Physician")
    private Physician physician;

    @ManyToOne
    @JoinColumn(name = "AssistingNurse")
    private Nurse assistingNurse;

    // ✅ Default Constructor
    public Undergoes() {}

    // ✅ Parameterized Constructor
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

    // ✅ Getters & Setters

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
