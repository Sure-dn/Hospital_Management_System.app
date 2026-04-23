package com.sprint.project.nurseoncallroom.exception;

public class NurseAlreadyAssignedException extends RuntimeException {
    public NurseAlreadyAssignedException(Integer nurseId) {
        super("Nurse already assigned with id: " + nurseId);
    }
}
