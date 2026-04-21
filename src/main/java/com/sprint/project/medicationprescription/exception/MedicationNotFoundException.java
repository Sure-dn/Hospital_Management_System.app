package com.sprint.project.medicationprescription.exception;

public class MedicationNotFoundException extends RuntimeException {

    public MedicationNotFoundException(Integer code) {
        super("Medication not found with code: " + code);
    }
}