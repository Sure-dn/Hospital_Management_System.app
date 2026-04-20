package com.sprint.project.nurseoncallroom.exception;

public class OnCallNotFoundException extends RuntimeException {

    public OnCallNotFoundException(String message) {
        super(message);
    }

    public OnCallNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
