package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@IdClass(BlockId.class)
public class Block {

    @Id
    @NotNull(message = "Block floor cannot be null")
    @Min(value = 0, message = "Block floor must be non-negative")
    @Column(name = "BlockFloor")
    private Integer blockFloor;

    @Id
    @NotNull(message = "Block code cannot be null")
    @Min(value = 1, message = "Block code must be greater than 0")
    @Column(name = "BlockCode")
    private Integer blockCode;

    // 🔗 Relationship: One Block -> Many Rooms
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<Room> rooms;

    // Getters and Setters

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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}