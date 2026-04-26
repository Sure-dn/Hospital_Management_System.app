package com.sprint.project.patientAppointment.dto.requestdto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {
	   @NotNull(message = "Appointment ID is required")
	    private Integer appointmentId;

	    @NotNull(message = "Patient SSN is required")
	    private Integer patientSsn;

	    private Integer prepNurseId;

	    @NotNull(message = "Physician ID is required")
	    private Integer physicianId;

	    @NotNull(message = "Start time is required")
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime starttime;

	    @NotNull(message = "End time is required")
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime endtime;

	    @NotBlank(message = "Examination room cannot be blank")
	    private String examinationRoom;

	    // Getters and Setters
		public Integer getAppointmentId() {
			return appointmentId;
		}

		public void setAppointmentId(Integer appointmentId) {
			this.appointmentId = appointmentId;
		}

		public Integer getPatientSsn() {
			return patientSsn;
		}

		public void setPatientSsn(Integer patientSsn) {
			this.patientSsn = patientSsn;
		}

		public Integer getPrepNurseId() {
			return prepNurseId;
		}

		public void setPrepNurseId(Integer prepNurseId) {
			this.prepNurseId = prepNurseId;
		}

		public Integer getPhysicianId() {
			return physicianId;
		}

		public void setPhysicianId(Integer physicianId) {
			this.physicianId = physicianId;
		}

		public LocalDateTime getStarttime() {
			return starttime;
		}

		public void setStarttime(LocalDateTime starttime) {
			this.starttime = starttime;
		}

		public LocalDateTime getEndtime() {
			return endtime;
		}

		public void setEndtime(LocalDateTime endtime) {
			this.endtime = endtime;
		}

		public String getExaminationRoom() {
			return examinationRoom;
		}

		public void setExaminationRoom(String examinationRoom) {
			this.examinationRoom = examinationRoom;
		}
}

