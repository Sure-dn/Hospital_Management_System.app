package com.sprint.project.nurseoncallroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomNumber;

    @NotBlank(message = "Room type cannot be blank")
    @Size(min = 3, max = 20)
    @Column(name = "type")
    private String type;

    @NotNull(message = "Unavailable flag cannot be null")
    @Column(name = "unavailable")
    private Boolean unavailable;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "block_floor", referencedColumnName = "block_floor"),
            @JoinColumn(name = "block_code", referencedColumnName = "block_code")
    })
    private BlockEntity block;

    // ✅ Default constructor
    public RoomEntity() {}

    // ✅ Correct constructor
    public RoomEntity(Integer roomNumber, String type, Boolean unavailable, BlockEntity block) {
        this.roomNumber = roomNumber;   // ✅ FIXED
        this.type = type;
        this.unavailable = unavailable;
        this.block = block;
    }

    // Getters & Setters
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setRoomType(String type) {
        this.type = type;
    }

    public Boolean getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Boolean unavailable) {
        this.unavailable = unavailable;
    }

    public BlockEntity getBlock() {
        return block;
    }

    public void setBlock(BlockEntity block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", unavailable=" + unavailable +
                ", block=" + block +
                '}';
    }
}