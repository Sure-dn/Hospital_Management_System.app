
	package com.sprint.project.patientAppointment.entity;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;

	import java.time.LocalDateTime;


import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;

	@Entity
	@Table(name = "Appointment")
public class AppointmentEntity {

    @Id
    @NotNull
    @Column(name = "AppointmentID")
    private Integer appointmentId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "PrepNurse", referencedColumnName = "employee_id"
)
    private NurseEntity prepNurse;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "employee_id"
, nullable = false)
    private PhysicianEntity physician;

    @NotNull
    @Column(name = "Starttime", nullable = false)
    private LocalDateTime starttime;

    @NotNull
    @Column(name = "Endtime", nullable = false)
    private LocalDateTime endtime;

    @NotBlank
    @Column(name = "ExaminationRoom", nullable = false, length = 10)
    private String examinationRoom;

    public AppointmentEntity() {}

    // Getters and Setters

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public NurseEntity getPrepNurse() {
        return prepNurse;
    }

    public void setPrepNurse(NurseEntity prepNurse) {
        this.prepNurse = prepNurse;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public LocalDateTime getStart() {
        return starttime;
    }

    public void setStart(LocalDateTime start) {
        this.starttime = start;
    }

    public LocalDateTime getEnd() {
        return endtime;
    }

    public void setEnd(LocalDateTime end) {
        this.endtime = end;
    }

    public String getExaminationRoom() {
        return examinationRoom;
    }

    public void setExaminationRoom(String examinationRoom) {
        this.examinationRoom = examinationRoom;
    }
}



