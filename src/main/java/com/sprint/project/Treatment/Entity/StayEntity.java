package com.sprint.project.Treatment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Objects;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.RoomEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;

@Entity
@Table(name = "Stay")
public class StayEntity {

    @Id
    @NotNull(message = "Stay ID cannot be null")
    @Positive(message = "Stay ID must be a positive number")
    @Column(name = "StayID")
    private Integer stayId;

    @NotNull(message = "Patient cannot be null")
    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "SSN", nullable = false)
    private PatientEntity patient;

    @NotNull(message = "Room cannot be null")
    @ManyToOne
    @JoinColumn(name = "Room", referencedColumnName = "RoomNumber", nullable = false)
    private RoomEntity room;

    @NotNull(message = "Stay start date cannot be null")
    @Column(name = "StayStart", nullable = false)
    private LocalDateTime stayStart;

    @Column(name = "StayEnd")
    private LocalDateTime stayEnd;

    public StayEntity() {}

    public StayEntity(Integer stayId, PatientEntity patient, RoomEntity room,
                LocalDateTime stayStart, LocalDateTime stayEnd) {
        this.stayId = stayId;
        this.patient = patient;
        this.room = room;
        this.stayStart = stayStart;
        this.stayEnd = stayEnd;
    }

    // Getters and Setters

    public Integer getStayId() {
        return stayId;
    }

    public void setStayId(Integer stayId) {
        this.stayId = stayId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public RoomEntity getRoom() {
        return room;
    }

	public void setRoom(RoomEntity room) {
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
    
    @Override
	public String toString() {
		return "StayEntity [stayId=" + stayId + ", patient=" + patient + ", room=" + room + ", stayStart=" + stayStart
				+ ", stayEnd=" + stayEnd + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(patient, room, stayEnd, stayId, stayStart);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StayEntity other = (StayEntity) obj;
		return Objects.equals(patient, other.patient) && Objects.equals(room, other.room)
				&& Objects.equals(stayEnd, other.stayEnd) && Objects.equals(stayId, other.stayId)
				&& Objects.equals(stayStart, other.stayStart);
	}
    
}
