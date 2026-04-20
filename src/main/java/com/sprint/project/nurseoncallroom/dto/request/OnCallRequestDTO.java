package com.sprint.project.nurseoncallroom.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OnCallRequestDTO {

    @NotNull(message = "Block floor is required")
    private Integer blockFloor;

    @NotNull(message = "Block code is required")
    private Integer blockCode;

    @NotNull(message = "On-call start time is required")
    private LocalDateTime onCallStart;

    @NotNull(message = "On-call end time is required")
    private LocalDateTime onCallEnd;

    public OnCallRequestDTO() {}

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public LocalDateTime getOnCallStart() { return onCallStart; }
    public void setOnCallStart(LocalDateTime onCallStart) { this.onCallStart = onCallStart; }

    public LocalDateTime getOnCallEnd() { return onCallEnd; }
    public void setOnCallEnd(LocalDateTime onCallEnd) { this.onCallEnd = onCallEnd; }
}