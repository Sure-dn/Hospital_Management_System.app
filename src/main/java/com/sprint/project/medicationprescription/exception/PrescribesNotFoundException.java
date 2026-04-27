package com.sprint.project.medicationprescription.exception;

public class PrescribesNotFoundException extends RuntimeException {

    public PrescribesNotFoundException(String message) {
        super(message);
    }

    public PrescribesNotFoundException() {
        super();
    }
}