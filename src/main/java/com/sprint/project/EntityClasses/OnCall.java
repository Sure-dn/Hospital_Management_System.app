package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@IdClass(OnCallId.class)
@Table(name = "On_Call")
public class OnCall {

    @NotNull(message = "Nurse cannot be null")
    @ManyToOne
    @JoinColumn(name = "Nurse")
    private Nurse nurse;

    @Id
    @NotNull(message = "Block floor is required")
    @Positive(message = "Block floor must be positive")
    @Column(name = "BlockFloor")
    private Integer blockFloor;

    @Id
    @NotNull(message = "Block code is required")
    @Positive(message = "Block code must be positive")
    @Column(name = "BlockCode")
    private Integer blockCode;

    @Id
    @NotNull(message = "OnCall start time is required")
    @PastOrPresent(message = "Start time cannot be in future")
    @Column(name = "OnCallStart")
    private LocalDateTime onCallStart;

    @Id
    @NotNull(message = "OnCall end time is required")
    @FutureOrPresent(message = "End time must be present or future")
    @Column(name = "OnCallEnd")
    private LocalDateTime onCallEnd;

    // Default Constructor
    public OnCall() {}

    // Parameterized Constructor
    public OnCall(Nurse nurse, Integer blockFloor, Integer blockCode,
                  LocalDateTime onCallStart, LocalDateTime onCallEnd) {
        this.nurse = nurse;
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.onCallStart = onCallStart;
        this.onCallEnd = onCallEnd;
    }

    // Getters and Setters

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Integer getBlockFloor() {
        return blockFloor;
    }

    public void setBlockFloor(Integer blockFloor) {
        this.blockFloor = blockFloor;
    }

    public Integer getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(Integer blockCode) {
        this.blockCode = blockCode;
    }

    public LocalDateTime getOnCallStart() {
        return onCallStart;
    }

    public void setOnCallStart(LocalDateTime onCallStart) {
        this.onCallStart = onCallStart;
    }

    public LocalDateTime getOnCallEnd() {
        return onCallEnd;
    }

    public void setOnCallEnd(LocalDateTime onCallEnd) {
        this.onCallEnd = onCallEnd;
    }
}