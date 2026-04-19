package com.sprint.project.NurseOnCallRoomAPIs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BlockId implements Serializable {

    @Column(name = "block_floor")   // ✅ MUST MATCH DB
    private Integer blockFloor;

    @Column(name = "block_code")    // ✅ MUST MATCH DB
    private Integer blockCode;

    public BlockId() {}

    public BlockId(Integer blockFloor, Integer blockCode) {
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
    }

    // equals() and hashCode() REQUIRED
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockId)) return false;
        BlockId that = (BlockId) o;
        return Objects.equals(blockFloor, that.blockFloor) &&
                Objects.equals(blockCode, that.blockCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockFloor, blockCode);
    }
}