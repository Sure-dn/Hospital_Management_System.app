package com.sprint.project.nurseoncallroom.exception;

import java.time.LocalDateTime;

public class InvalidShiftTimeException extends RuntimeException {

    public InvalidShiftTimeException(String message) {
        super(message);
    }
}

