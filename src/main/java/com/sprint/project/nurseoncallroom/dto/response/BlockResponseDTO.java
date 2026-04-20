package com.sprint.project.nurseoncallroom.dto.response;

public class BlockResponseDTO {

    private Integer blockFloor;
    private Integer blockCode;
    private String name;

    public BlockResponseDTO() {}

    public BlockResponseDTO(Integer blockFloor, Integer blockCode, String name) {
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.name = name;
    }

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
