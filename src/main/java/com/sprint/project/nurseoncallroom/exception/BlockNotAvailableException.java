package com.sprint.project.nurseoncallroom.exception;

public class BlockNotAvailableException extends RuntimeException {
    public BlockNotAvailableException(Integer blockId, Integer roomId) {
        super("Block with ID " + blockId + " and Room ID " + roomId + " is not available");
    }
}
