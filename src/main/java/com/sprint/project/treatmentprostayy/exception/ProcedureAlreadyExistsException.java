package com.sprint.project.treatmentprostayy.exception;

public class ProcedureAlreadyExistsException extends RuntimeException {
    public ProcedureAlreadyExistsException(String message) {
        super(message);
    }
}