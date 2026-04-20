package com.sprint.project.nurseoncallroom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomRequestDTO {

    @NotBlank(message = "Room type is required")
    private String type;

    @NotNull(message = "Unavailable status is required")
    private Boolean unavailable;

    @NotNull(message = "Block floor is required")
    private Integer blockFloor;

    @NotNull(message = "Block code is required")
    private Integer blockCode;

    public RoomRequestDTO() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getUnavailable() { return unavailable; }
    public void setUnavailable(Boolean unavailable) { this.unavailable = unavailable; }

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }
}