package com.sprint.project.EntityClasses;

import jakarta.persistence.*;

@Entity
@IdClass(BlockId.class)
public class Block {

    @Id
    @Column(name = "BlockFloor")
    private Integer blockFloor;

    @Id
    @Column(name = "BlockCode")
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