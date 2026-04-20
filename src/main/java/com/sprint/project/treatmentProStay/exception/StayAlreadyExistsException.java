package com.sprint.project.treatmentprostay.exception;

public class StayAlreadyExistsException extends RuntimeException {
    public StayAlreadyExistsException(String message) {
        super(message);
    }
}