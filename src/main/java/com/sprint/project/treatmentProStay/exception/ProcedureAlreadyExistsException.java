package com.sprint.project.treatmentprostay.exception;

public class ProcedureAlreadyExistsException extends RuntimeException {
    public ProcedureAlreadyExistsException(String message) {
        super(message);
    }
}