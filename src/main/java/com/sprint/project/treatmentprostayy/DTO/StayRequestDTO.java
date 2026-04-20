package com.sprint.project.treatmentprostayy.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StayRequestDTO {
	 	@NotNull
	    @Positive
	    private Integer stayId;

	    @NotNull
	    private Integer patientId;

	    @NotNull
	    private Integer roomId;

	    @NotNull
	    private LocalDateTime stayStart;

	    private LocalDateTime stayEnd;

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
