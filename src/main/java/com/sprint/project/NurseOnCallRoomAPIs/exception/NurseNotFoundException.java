package com.sprint.project.NurseOnCallRoomAPIs.exception;

public class NurseNotFoundException extends ResourceNotFoundException {

    public NurseNotFoundException(Integer employeeId) {
        super("Nurse", "employeeId", employeeId);
    }

    public NurseNotFoundException(String message) {
        super(message);
    }
}
