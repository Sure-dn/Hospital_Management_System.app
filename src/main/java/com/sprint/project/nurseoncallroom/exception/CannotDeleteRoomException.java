package com.sprint.project.nurseoncallroom.exception;

public class CannotDeleteRoomException extends RuntimeException {
    public CannotDeleteRoomException() {
        super("Cannot delete room because it is assigned or occupied");
    }
}
