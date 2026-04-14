package com.sprint.project.EntityClasses;

import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @Column(name = "RoomNumber")
    private Integer roomNumber;

    @Column(name = "RoomType", nullable = false)
    private String roomType;

    @Column(name = "Unavailable", nullable = false)
    private Boolean unavailable;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BlockFloor", referencedColumnName = "BlockFloor"),
        @JoinColumn(name = "BlockCode", referencedColumnName = "BlockCode")
    })
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