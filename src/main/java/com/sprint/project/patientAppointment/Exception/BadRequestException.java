package com.sprint.project.patientAppointment.Exception;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
        super(message);
    }

}
