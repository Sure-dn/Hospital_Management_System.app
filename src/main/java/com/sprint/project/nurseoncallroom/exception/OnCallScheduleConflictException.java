package com.sprint.project.nurseoncallroom.exception;

public class OnCallScheduleConflictException extends RuntimeException {
    public OnCallScheduleConflictException() {
        super("OnCall schedule conflicts with existing schedule");
    }
}
