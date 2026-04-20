package com.sprint.project.treatmentprostay.exception;

public class TreatmentAlreadyExistsException extends RuntimeException {
    public TreatmentAlreadyExistsException(String message) {
        super(message);
    }
}