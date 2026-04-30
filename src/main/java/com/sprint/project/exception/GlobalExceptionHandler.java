package com.sprint.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.medicationprescription.exception.*;   // ← Add this

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 NOT FOUND (404)
    @ExceptionHandler({
        ResourceNotFoundException.class,
        MedicationNotFoundException.class,
        PrescribesNotFoundException.class
        // Add other NotFound exceptions from other modules if any
    })
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // 🟡 ALREADY EXISTS / DUPLICATE (409)
    @ExceptionHandler({
        DuplicateResourceException.class,
        DuplicateMedicationException.class
        // Add others if you have
    })
    public ResponseEntity<String> handleAlreadyExists(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // 🔵 INVALID / BAD REQUEST (400)
    @ExceptionHandler({
        InvalidRequestException.class,
        ValidationException.class,
        InvalidMedicationDataException.class,
        InvalidDoseException.class,
        BadRequestException.class
    })
    public ResponseEntity<String> handleInvalid(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // ⚫ GENERAL (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong: " + ex.getMessage());
    }
}