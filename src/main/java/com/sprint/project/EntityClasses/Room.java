package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Room {

    @Id
    @Column(name = "RoomNumber")
    @NotNull(message = "Room number cannot be null")
    @Min(value = 1, message = "Room number must be at least 1")
    private Integer roomNumber;

    @Column(name = "RoomType", nullable = false)
    @NotBlank(message = "Room type cannot be empty")
    private String roomType;

    @Column(name = "Unavailable", nullable = false)
    @NotNull(message = "Unavailable status cannot be null")
    private Boolean unavailable;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BlockFloor", referencedColumnName = "BlockFloor"),
        @JoinColumn(name = "BlockCode", referencedColumnName = "BlockCode")
    })
    @NotNull(message = "Block cannot be null")
    private Block block;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Boolean getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Boolean unavailable) {
        this.unavailable = unavailable;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}