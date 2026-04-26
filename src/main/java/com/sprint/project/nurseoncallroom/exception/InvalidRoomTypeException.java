package com.sprint.project.nurseoncallroom.exception;

public class InvalidRoomTypeException extends RuntimeException {
    public InvalidRoomTypeException(String type) {
        super("Invalid room type: " + type);
    }
}
