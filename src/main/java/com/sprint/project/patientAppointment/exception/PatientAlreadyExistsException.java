package com.sprint.project.patientAppointment.exception;

public class PatientAlreadyExistsException extends RuntimeException {
	public PatientAlreadyExistsException(String message) {
        super(message);
    }
}

