package com.sprint.project.nurseoncallroom.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class OnCallId implements Serializable {

    private Integer nurse;   // must match entity field name

    private Integer blockFloor;
    private Integer blockCode;
    private LocalDateTime onCallStart;

    public OnCallId() {}

    public OnCallId(Integer nurse, Integer blockFloor, Integer blockCode, LocalDateTime onCallStart) {
        this.nurse = nurse;
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.onCallStart = onCallStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OnCallId)) return false;
        OnCallId that = (OnCallId) o;
        return Objects.equals(nurse, that.nurse) &&
                Objects.equals(blockFloor, that.blockFloor) &&
                Objects.equals(blockCode, that.blockCode) &&
                Objects.equals(onCallStart, that.onCallStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nurse, blockFloor, blockCode, onCallStart);
    }
}