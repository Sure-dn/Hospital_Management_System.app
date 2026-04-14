package com.sprint.project.EntityClasses;

import java.io.Serializable;
import java.util.Objects;

public class BlockId implements Serializable {

    private Integer blockFloor;
    private Integer blockCode;

    public BlockId() {}

    public BlockId(Integer blockFloor, Integer blockCode) {
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
    }

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
