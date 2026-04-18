package com.sprint.project.NurseOnCallRoomAPIs.dto.response;


import java.time.LocalDateTime;

public class OnCallResponseDTO {

    private Integer nurseEmployeeId;
    private String nurseName;
    private Integer blockFloor;
    private Integer blockCode;
    private String blockName;
    private LocalDateTime onCallStart;
    private LocalDateTime onCallEnd;

    public OnCallResponseDTO() {}

    public Integer getNurseEmployeeId() { return nurseEmployeeId; }
    public void setNurseEmployeeId(Integer nurseEmployeeId) { this.nurseEmployeeId = nurseEmployeeId; }

    public String getNurseName() { return nurseName; }
    public void setNurseName(String nurseName) { this.nurseName = nurseName; }

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }

    public LocalDateTime getOnCallStart() { return onCallStart; }
    public void setOnCallStart(LocalDateTime onCallStart) { this.onCallStart = onCallStart; }

    public LocalDateTime getOnCallEnd() { return onCallEnd; }
    public void setOnCallEnd(LocalDateTime onCallEnd) { this.onCallEnd = onCallEnd; }
}
