package com.sprint.project.nurseoncallroom.exception;

public class RoomAlreadyOccupiedException extends RuntimeException {
    public RoomAlreadyOccupiedException(Integer roomId) {
        super("Room already occupied with id: " + roomId);
    }
}
