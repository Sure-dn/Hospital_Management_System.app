package com.sprint.project.treatmentprostayy.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class UndergoesRequestDTO {

    @NotNull
    private Integer patientId;

    @NotNull
    private Integer procedureId;

    @NotNull
    private Integer stayId;

    @NotNull
    private LocalDateTime dateUndergoes;

    private Integer physicianId;
    private Integer nurseId;
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}
	public Integer getStayId() {
		return stayId;
	}
	public void setStayId(Integer stayId) {
		this.stayId = stayId;
	}
	public LocalDateTime getDateUndergoes() {
		return dateUndergoes;
	}
	public void setDateUndergoes(LocalDateTime dateUndergoes) {
		this.dateUndergoes = dateUndergoes;
	}
	public Integer getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}
	public Integer getNurseId() {
		return nurseId;
	}
	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}

    
}