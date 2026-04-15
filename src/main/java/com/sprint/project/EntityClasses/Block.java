package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@IdClass(BlockId.class)
public class Block {

    @Id
    @Column(name = "BlockFloor")
    @NotNull(message = "Block floor cannot be null")
    @Min(value = 0, message = "Block floor must be 0 or greater")
    private Integer blockFloor;

    @Id
    @Column(name = "BlockCode")
    @NotNull(message = "Block code cannot be null")
    @Min(value = 1, message = "Block code must be at least 1")
    private Integer blockCode;

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
}