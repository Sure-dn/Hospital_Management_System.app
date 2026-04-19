package com.sprint.project.NurseOnCallRoomAPIs.exception;

public class OnCallConflictException extends NurseInvalidRequestException {

    public OnCallConflictException(Integer employeeId) {
        super("Nurse with employeeId " + employeeId + " already has an overlapping on-call shift");
    }

    public OnCallConflictException(String message) {
        super(message);
    }
}
