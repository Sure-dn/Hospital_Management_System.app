package com.sprint.project.treatmentprostayy.dto;

import java.time.LocalDateTime;

public class StayResponseDTO {

    private Integer stayId;
    private Integer patientId;
    private Integer roomId;
    private LocalDateTime stayStart;
    private LocalDateTime stayEnd;

    public StayResponseDTO(Integer stayId, Integer patientId, Integer roomId,
                           LocalDateTime stayStart, LocalDateTime stayEnd) {
        this.stayId = stayId;
        this.patientId = patientId;
        this.roomId = roomId;
        this.stayStart = stayStart;
        this.stayEnd = stayEnd;
    }

	public Integer getStayId() {
		return stayId;
	}

	public void setStayId(Integer stayId) {
		this.stayId = stayId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public LocalDateTime getStayStart() {
		return stayStart;
	}

	public void setStayStart(LocalDateTime stayStart) {
		this.stayStart = stayStart;
	}

	public LocalDateTime getStayEnd() {
		return stayEnd;
	}

	public void setStayEnd(LocalDateTime stayEnd) {
		this.stayEnd = stayEnd;
	}
    
}
