package com.sprint.project.nurseoncallroom.entity;

import jakarta.persistence.*;

@Entity
@IdClass(com.sprint.project.nurseoncallroom.entity.BlockId.class)
@Table(name = "block")
public class BlockEntity {

    @Id
    @Column(name = "block_floor")   // ✅ FIX
    private Integer blockFloor;

    @Id
    @Column(name = "block_code")    // ✅ FIX
    private Integer blockCode;

    @Column(name = "name")
    private String name;

    // ✅ Default constructor
    public BlockEntity() {}

    // ✅ Correct constructor
    public BlockEntity(Integer blockFloor, Integer blockCode, String name) {
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.name = name;
    }

    // Getters & Setters
    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "BlockEntity{" +
                "blockFloor=" + blockFloor +
                ", blockCode=" + blockCode +
                ", name='" + name + '\'' +
                '}';
    }
}