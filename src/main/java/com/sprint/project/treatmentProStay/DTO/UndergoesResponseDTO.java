package com.sprint.project.treatmentprostay.DTO;

import java.time.LocalDateTime;

public class UndergoesResponseDTO {

    private Integer patientId;
    private Integer procedureId;
    private Integer stayId;
    private LocalDateTime dateUndergoes;
    private Integer physicianId;
    private Integer nurseId;

    public UndergoesResponseDTO(Integer patientId, Integer procedureId,
                                Integer stayId, LocalDateTime dateUndergoes,
                                Integer physicianId, Integer nurseId) {
        this.patientId = patientId;
        this.procedureId = procedureId;
        this.stayId = stayId;
        this.dateUndergoes = dateUndergoes;
        this.physicianId = physicianId;
        this.nurseId = nurseId;
    }

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
