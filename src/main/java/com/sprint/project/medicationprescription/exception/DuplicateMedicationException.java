package com.sprint.project.medicationprescription.exception;

public class DuplicateMedicationException extends RuntimeException {

    // Constructor with message
    public DuplicateMedicationException(String message) {
        super(message);
    }

    // Optional: default constructor
    public DuplicateMedicationException() {
        super();
    }
}