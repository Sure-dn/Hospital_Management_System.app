package com.sprint.project.medicationprescription.exception;

public class InvalidMedicationDataException extends RuntimeException {

    public InvalidMedicationDataException(String message) {
        super(message);
    }

     public InvalidMedicationDataException() {
        super();
    }
}