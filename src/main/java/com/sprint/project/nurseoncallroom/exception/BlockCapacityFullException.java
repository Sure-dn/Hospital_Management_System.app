package com.sprint.project.nurseoncallroom.exception;

public class BlockCapacityFullException extends RuntimeException {
    public BlockCapacityFullException(Integer floor, Integer code) {
        super("Block capacity full for floor: " + floor + ", code: " + code);
    }
}
