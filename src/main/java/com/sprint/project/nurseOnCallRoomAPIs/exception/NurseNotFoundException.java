package com.sprint.project.nurseOnCallRoomAPIs.exception;

public class NurseNotFoundException extends OnCallNotFoundException {

    public NurseNotFoundException(Integer employeeId) {
        super("Nurse", "employeeId", employeeId);
    }

    public NurseNotFoundException(String message) {
        super(message);
    }
}
