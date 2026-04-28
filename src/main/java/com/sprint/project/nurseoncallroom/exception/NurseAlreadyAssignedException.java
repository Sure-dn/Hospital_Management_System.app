package com.sprint.project.nurseoncallroom.exception;

public class NurseAlreadyAssignedException extends RuntimeException {
    public NurseAlreadyAssignedException(Integer nurseId) {
        super("Nurse with ID " + nurseId + " is already assigned");
    }
    }

