package com.sprint.project.nurseOnCallRoomAPIs.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BlockRequestDTO {

    @NotNull(message = "Block floor is required")
    private Integer blockFloor;

    @NotNull(message = "Block code is required")
    private Integer blockCode;

    @NotBlank(message = "Block name is required")
    private String name;

    public BlockRequestDTO() {}

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}