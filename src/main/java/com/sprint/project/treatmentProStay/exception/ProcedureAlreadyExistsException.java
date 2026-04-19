package com.sprint.project.treatmentProStay.exception;

public class ProcedureAlreadyExistsException extends RuntimeException {
    public ProcedureAlreadyExistsException(String message) {
        super(message);
    }
}