package com.sprint.project.nurseoncallroom.exception;

public class BlockNotAvailableException extends RuntimeException {
    public BlockNotAvailableException(Integer floor, Integer code) {
        super("Block not available with floor: " + floor + " and code: " + code);
    }
}
