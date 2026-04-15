package com.sprint.project.NurseOnCallRoomAPIs.Entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class OnCallId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer nurse;
    private Integer blockFloor;
    private Integer blockCode;
    private LocalDateTime onCallStart;
    private LocalDateTime onCallEnd;

    public OnCallId() {}

    public OnCallId(Integer nurse, Integer blockFloor, Integer blockCode,
                    LocalDateTime onCallStart, LocalDateTime onCallEnd) {
        this.nurse = nurse;
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.onCallStart = onCallStart;
        this.onCallEnd = onCallEnd;
    }

    // Getters and Setters

    public Integer getNurse() {
        return nurse;
    }

    public void setNurse(Integer nurse) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OnCallId)) return false;
        OnCallId that = (OnCallId) o;
        return Objects.equals(nurse, that.nurse) &&
               Objects.equals(blockFloor, that.blockFloor) &&
               Objects.equals(blockCode, that.blockCode) &&
               Objects.equals(onCallStart, that.onCallStart) &&
               Objects.equals(onCallEnd, that.onCallEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nurse, blockFloor, blockCode, onCallStart, onCallEnd);
    }
}

