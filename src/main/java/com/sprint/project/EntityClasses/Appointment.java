package com.sprint.project.EntityClasses;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    private Integer appointmentId;

    private Integer patient;      // FK → Patient(SSN)
    private Integer prepNurse;    // FK → Nurse(EmployeeID)
    private Integer physician;   // FK → Physician(EmployeeID)

    private LocalDateTime starto;
    private LocalDateTime endo;

    private String examinationRoom;

    // Default Constructor
    public Appointment() {
    }

    // Parameterized Constructor
    public Appointment(Integer appointmentId, Integer patient, Integer prepNurse,
                       Integer physician, LocalDateTime starto,
                       LocalDateTime endo, String examinationRoom) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.prepNurse = prepNurse;
        this.physician = physician;
        this.starto = starto;
        this.endo = endo;
        this.examinationRoom = examinationRoom;
    }

    // Getters and Setters
    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getPrepNurse() {
        return prepNurse;
    }

    public void setPrepNurse(Integer prepNurse) {
        this.prepNurse = prepNurse;
    }

    public Integer getPhysician() {
        return physician;
    }

    public void setPhysician(Integer physician) {
        this.physician = physician;
    }

    public LocalDateTime getStarto() {
        return starto;
    }

    public void setStarto(LocalDateTime starto) {
        this.starto = starto;
    }

    public LocalDateTime getEndo() {
        return endo;
    }

    public void setEndo(LocalDateTime endo) {
        this.endo = endo;
    }

    public String getExaminationRoom() {
        return examinationRoom;
    }

    public void setExaminationRoom(String examinationRoom) {
        this.examinationRoom = examinationRoom;
    }
    //text file
}