package com.sprint.project.NurseOnCallRoomAPIs.exception;

public class BlockNotFoundException extends OnCallNotFoundException {

    public BlockNotFoundException(Integer floor, Integer code) {
        super("Block", "floor/code", floor + "/" + code);
    }

    public BlockNotFoundException(String message) {
        super(message);
    }
}
