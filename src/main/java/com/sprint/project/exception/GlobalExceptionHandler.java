package com.sprint.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.treatmentprostayy.exception.InvalidProcedureException;
import com.sprint.project.treatmentprostayy.exception.InvalidStayException;
import com.sprint.project.treatmentprostayy.exception.InvalidTreatmentException;
import com.sprint.project.treatmentprostayy.exception.ProcedureAlreadyExistsException;
import com.sprint.project.treatmentprostayy.exception.ProcedureNotFoundException;
import com.sprint.project.treatmentprostayy.exception.StayAlreadyExistsException;
import com.sprint.project.treatmentprostayy.exception.StayNotFoundException;
import com.sprint.project.treatmentprostayy.exception.TreatmentAlreadyExistsException;
import com.sprint.project.treatmentprostayy.exception.TreatmentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 NOT FOUND EXCEPTIONS (404)
    @ExceptionHandler({
            ProcedureNotFoundException.class,
            StayNotFoundException.class,
            TreatmentNotFoundException.class
    })
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // 🟡 ALREADY EXISTS (409)
    @ExceptionHandler({
            ProcedureAlreadyExistsException.class,
            StayAlreadyExistsException.class,
            TreatmentAlreadyExistsException.class
    })
    public ResponseEntity<String> handleAlreadyExists(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    // 🔵 INVALID INPUT (400)
    @ExceptionHandler({
            InvalidProcedureException.class,
            InvalidStayException.class,
            InvalidTreatmentException.class
    })
    public ResponseEntity<String> handleInvalid(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // ⚫ GENERAL ERROR (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong: " + ex.getMessage());
    }
}