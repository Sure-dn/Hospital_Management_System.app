package com.sprint.project.nurseoncallroom.exception;

import java.time.LocalDateTime;

public class InvalidShiftTimeException extends RuntimeException {
    public InvalidShiftTimeException(LocalDateTime start, LocalDateTime end) {
        super("Invalid shift time: Start " + start + " must be before End " + end);
    }
}
