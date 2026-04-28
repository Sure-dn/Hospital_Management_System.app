package com.sprint.project.medicationprescription.exception;

public class InvalidDoseException extends RuntimeException {

    public InvalidDoseException(String dose) {
        super("Invalid dose value: " + dose);
    }

     public InvalidDoseException() {
        super();
    }
}