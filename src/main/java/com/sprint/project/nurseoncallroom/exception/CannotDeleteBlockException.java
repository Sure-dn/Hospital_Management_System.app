package com.sprint.project.nurseoncallroom.exception;

public class CannotDeleteBlockException extends RuntimeException {
    public CannotDeleteBlockException() {
        super("Cannot delete block because it contains rooms");
    }
}
