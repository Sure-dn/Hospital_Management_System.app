package com.sprint.project.nurseoncallroom.exception;

public class RoomNotFoundException extends OnCallNotFoundException {

    public RoomNotFoundException(Integer roomNumber) {
        super("Room", "roomNumber", roomNumber);
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
