package com.sprint.project.patientAppointment.DTO.ResponseDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentResponseDTO {
	private Integer appointmentId;
    private Integer patientSsn;
    private String patientName;
    private Integer prepNurseId;
    private String prepNurseName;
    private Integer physicianId;
    private String physicianName;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime starttime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endtime;

    private String examinationRoom;

    public AppointmentResponseDTO() {}
    
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPrepNurseId() {
		return prepNurseId;
	}

	public void setPrepNurseId(Integer prepNurseId) {
		this.prepNurseId = prepNurseId;
	}

	public String getPrepNurseName() {
		return prepNurseName;
	}

	public void setPrepNurseName(String prepNurseName) {
		this.prepNurseName = prepNurseName;
	}

	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
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
