package com.sprint.project.nurseoncallroom.exception;

public class BlockNotFoundException extends OnCallNotFoundException {

    public BlockNotFoundException(Integer floor, Integer code) {
        super("Block", "floor/code", floor + "/" + code);
    }

    public BlockNotFoundException(String message) {
        super(message);
    }
}
