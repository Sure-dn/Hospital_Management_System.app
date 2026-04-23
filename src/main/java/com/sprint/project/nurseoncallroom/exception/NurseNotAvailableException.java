package com.sprint.project.nurseoncallroom.exception;

public class NurseNotAvailableException extends RuntimeException {
    public NurseNotAvailableException(Integer nurseId) {
        super("Nurse not available with id: " + nurseId);
    }
}
