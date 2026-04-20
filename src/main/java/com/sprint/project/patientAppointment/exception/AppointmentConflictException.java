package com.sprint.project.patientAppointment.exception;

public class AppointmentConflictException extends RuntimeException {
	public AppointmentConflictException(String message) {
        super(message);
    }
}
