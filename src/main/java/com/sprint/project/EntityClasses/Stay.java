package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Stay")
public class Stay {

    @Id
    @Column(name = "StayID")
    private Integer stayID;

    @ManyToOne
    @JoinColumn(name = "Patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "Room")
    private Room room;

    @Column(name = "StayStart")
    private LocalDateTime stayStart;

    @Column(name = "StayEnd")
    private LocalDateTime stayEnd;

    // ✅ Default Constructor
    public Stay() {}

    // ✅ Parameterized Constructor
    public Stay(Integer stayID, Patient patient, Room room,
                LocalDateTime stayStart, LocalDateTime stayEnd) {
        this.stayID = stayID;
        this.patient = patient;
        this.room = room;
        this.stayStart = stayStart;
        this.stayEnd = stayEnd;
    }

    // ✅ Getters and Setters

    public Integer getStayID() {
        return stayID;
    }

    public void setStayID(Integer stayID) {
        this.stayID = stayID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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


