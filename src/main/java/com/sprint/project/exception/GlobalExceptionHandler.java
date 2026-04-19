package com.sprint.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.physicianDepartmentManagement.exception.DepartmentNotFoundException;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateAffiliationException;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateDepartmentException;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicatePhysicianException;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateTrainingException;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianDeletionException;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException;
import com.sprint.project.physicianDepartmentManagement.exception.PrimaryAffiliationException;
import com.sprint.project.physicianDepartmentManagement.exception.TrainingNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateResourceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobal(Exception ex) {
        return new ResponseEntity<>("Something went wrong: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleInvalid(InvalidRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(PhysicianNotFoundException.class)
    public ResponseEntity<String> handlePhysicianNotFound(PhysicianNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePhysicianException.class)
    public ResponseEntity<String> handleDuplicatePhysician(DuplicatePhysicianException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhysicianDeletionException.class)
    public ResponseEntity<String> handlePhysicianDelete(PhysicianDeletionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFound(DepartmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDepartmentException.class)
    public ResponseEntity<String> handleDuplicateDepartment(DuplicateDepartmentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(PrimaryAffiliationException.class)
    public ResponseEntity<String> handlePrimaryAffiliation(PrimaryAffiliationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateAffiliationException.class)
    public ResponseEntity<String> handleDuplicateAffiliation(DuplicateAffiliationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(TrainingNotFoundException.class)
    public ResponseEntity<String> handleTrainingNotFound(TrainingNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateTrainingException.class)
    public ResponseEntity<String> handleDuplicateTraining(DuplicateTrainingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

} 
