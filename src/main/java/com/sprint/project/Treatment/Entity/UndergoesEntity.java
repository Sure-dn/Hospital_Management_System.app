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

import com.sprint.project.NurseOnCallRoomAPIs.Entity.Nurse;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

@Entity
@Table(name = "Undergoes")
public class UndergoesEntity {
	@EmbeddedId
	private UndergoesId undergoesId;
    @NotNull(message = "Patient cannot be null")
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private PatientEntity patient;

    @NotNull(message = "Procedure cannot be null")
    @ManyToOne
    @JoinColumn(name = "Procedures", referencedColumnName = "Code", nullable = false)
    private ProceduresEntity procedures;

    @NotNull(message = "Stay cannot be null")
    @ManyToOne
    @JoinColumn(name = "Stay", referencedColumnName = "StayID", nullable = false)
    private StayEntity stay;

    @NotNull(message = "Date of procedure is required")
    @Column(name = "DateUndergoes", nullable = false)
    private LocalDateTime dateUndergoes;

    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID")
    private PhysicianEntity physician;

    @ManyToOne
    @JoinColumn(name = "AssistingNurse", referencedColumnName = "EmployeeID")
    private Nurse assistingNurse;

    public UndergoesEntity() {}

    public UndergoesEntity(PatientEntity patient, ProceduresEntity procedures, StayEntity stay,
                     LocalDateTime dateUndergoes, PhysicianEntity physician,
                     Nurse assistingNurse) {
        this.patient = patient;
        this.procedures = procedures;
        this.stay = stay;
        this.dateUndergoes = dateUndergoes;
        this.physician = physician;
        this.assistingNurse = assistingNurse;
    }

    // Getters and Setters

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public ProceduresEntity getProcedures() {
        return procedures;
    }

    public void setProcedures(ProceduresEntity procedures) {
        this.procedures = procedures;
    }

    public StayEntity getStay() {
        return stay;
    }

    public void setStay(StayEntity stay) {
        this.stay = stay;
    }

    public LocalDateTime getDateUndergoes() {
        return dateUndergoes;
    }

    public void setDateUndergoes(LocalDateTime dateUndergoes) {
        this.dateUndergoes = dateUndergoes;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public Nurse getAssistingNurse() {
        return assistingNurse;
    }

    public void setAssistingNurse(Nurse assistingNurse) {
        this.assistingNurse = assistingNurse;
    }
}

