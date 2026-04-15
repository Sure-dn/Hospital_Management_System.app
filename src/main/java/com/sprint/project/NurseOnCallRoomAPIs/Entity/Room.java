package com.sprint.project.NurseOnCallRoomAPIs.Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @NotNull(message = "Room number cannot be null")
    @Positive(message = "Room number must be positive")
    @Column(name = "RoomNumber")
    private Integer roomNumber;

    @NotBlank(message = "Room type cannot be empty")
    @Size(min = 3, max = 20, message = "Type must be between 3 and 20 characters")
    @Column(name = "RoomType", nullable = false, length = 20)
    private String roomType;

    @NotNull(message = "Availability is required")
    @Column(name = "Unavailable", nullable = false)
    private Boolean unavailable;

    @NotNull(message = "Block is required")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BlockFloor", referencedColumnName = "BlockFloor"),
        @JoinColumn(name = "BlockCode", referencedColumnName = "BlockCode")
    })
    private Block block;

    public Room() {}

    // Getters and Setters

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

