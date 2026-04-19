package com.sprint.project.treatmentProStay.exception;

public class StayAlreadyExistsException extends RuntimeException {
    public StayAlreadyExistsException(String message) {
        super(message);
    }
}