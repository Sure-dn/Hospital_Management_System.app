package com.sprint.project.exception;

import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException;
import com.sprint.project.treatmentprostayy.dto.ResponseStructure;
import com.sprint.project.treatmentprostayy.exception.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.patientAppointment.exception.PatientNotFoundException;
import com.sprint.project.treatmentprostayy.dto.ResponseStructure;
import com.sprint.project.treatmentprostayy.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 404 - NOT FOUND
    @ExceptionHandler({
            ProcedureNotFoundException.class,
            StayNotFoundException.class,
            TreatmentNotFoundException.class,
            BlockNotAvailableException.class,
            NurseNotAvailableException.class
    })
    public ResponseEntity<ResponseStructure<?>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseStructure<>(false, ex.getMessage(), null));
    }

    // 🟡 409 - ALREADY EXISTS
    @ExceptionHandler({
            ProcedureAlreadyExistsException.class,
            StayAlreadyExistsException.class,
            TreatmentAlreadyExistsException.class,
            NurseAlreadyAssignedException.class
    })
    public ResponseEntity<ResponseStructure<?>> handleAlreadyExists(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseStructure<>(false, ex.getMessage(), null));
    }

    // 🔵 400 - CUSTOM INVALID
    @ExceptionHandler({
            InvalidProcedureException.class,
            InvalidStayException.class,
            InvalidTreatmentException.class,
            InvalidShiftTimeException.class,
            OnCallScheduleConflictException.class
    })
    public ResponseEntity<ResponseStructure<?>> handleInvalid(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseStructure<>(false, ex.getMessage(), null));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseStructure<?>> handlePatientNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseStructure<>(false, ex.getMessage(), null));
    }



    // ✅ PARAM / PATH VALIDATION
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseStructure<?>> handleBindException(BindException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Invalid input");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseStructure<>(false, message, null));
    }

    // ✅ JSON PARSE ERROR
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseStructure<?>> handleNotReadable(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseStructure<>(false, "Invalid request body", null));
    }

    // ⚫ GENERAL ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseStructure<?>> handleGeneral(Exception ex) {
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseStructure<>(false, "Something went wrong", null));
    }
    //validation handeler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}