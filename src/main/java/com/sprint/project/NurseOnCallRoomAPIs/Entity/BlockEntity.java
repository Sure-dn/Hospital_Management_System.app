package com.sprint.project.NurseOnCallRoomAPIs.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Block")
@IdClass(BlockId.class)
public class BlockEntity {

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

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

    public BlockEntity() {}

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

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
}

