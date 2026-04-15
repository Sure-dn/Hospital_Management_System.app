package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @NotNull(message = "Room number cannot be null")
    @Positive(message = "Room number must be positive")
    private Integer roomNumber;

    @NotBlank(message = "Room type cannot be empty")
    @Size(min = 3, max = 20, message = "Type must be between 3 and 20 characters")
    private String type;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10, message = "Capacity cannot exceed 10")
    private Integer capacity;

    // 🔗 Relationship: Many Rooms → One Block (Composite Key)
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BlockFloor", referencedColumnName = "BlockFloor"),
        @JoinColumn(name = "BlockCode", referencedColumnName = "BlockCode")
    })
    @NotNull(message = "Block is required")
    private Block block;

    public Room() {}

    // Getters and Setters

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}