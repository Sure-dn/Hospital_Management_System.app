package com.sprint.project.physicianDepartmentManagement;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PhysicianEntity and DepartmentEntity validations.
 */
class PhysicianAndDepartmentValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ========================
    // PhysicianEntity Tests
    // ========================

    private PhysicianEntity validPhysician() {
        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1);
        p.setName("Dr. Emily Clark");
        p.setPosition("Neurologist");
        p.setSsn(123456789);
        return p;
    }

    @Test
    @DisplayName("Valid PhysicianEntity should have no violations")
    void validPhysician_noViolations() {
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(validPhysician());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null employeeId should fail @NotNull")
    void nullPhysicianEmployeeId_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setEmployeeId(null);
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("employeeId")));
    }

    @Test
    @DisplayName("Blank physician name should fail @NotBlank")
    void blankPhysicianName_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setName("");
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Null physician name should fail @NotBlank")
    void nullPhysicianName_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setName(null);
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Blank position should fail @NotBlank")
    void blankPhysicianPosition_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setPosition("   ");
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("position")));
    }

    @Test
    @DisplayName("Null position should fail @NotBlank")
    void nullPhysicianPosition_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setPosition(null);
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Null SSN should fail @NotNull")
    void nullPhysicianSsn_shouldViolate() {
        PhysicianEntity p = validPhysician();
        p.setSsn(null);
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("ssn")));
    }

    @Test
    @DisplayName("All null fields should produce multiple violations")
    void allNullPhysician_shouldProduceMultipleViolations() {
        PhysicianEntity p = new PhysicianEntity();
        Set<ConstraintViolation<PhysicianEntity>> violations = validator.validate(p);
        // employeeId, name, position, ssn all null → at least 4 violations
        assertTrue(violations.size() >= 4,
                "Expected at least 4 violations but got: " + violations.size());
    }

    // ========================
    // DepartmentEntity Tests
    // ========================

    private DepartmentEntity validDepartment() {
        DepartmentEntity dept = new DepartmentEntity();
        dept.setDepartmentId(10);
        dept.setName("Cardiology");
        dept.setHead(validPhysician());
        return dept;
    }

    @Test
    @DisplayName("Valid DepartmentEntity should have no violations")
    void validDepartment_noViolations() {
        Set<ConstraintViolation<DepartmentEntity>> violations = validator.validate(validDepartment());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null departmentId should fail @NotNull")
    void nullDepartmentId_shouldViolate() {
        DepartmentEntity dept = validDepartment();
        dept.setDepartmentId(null);
        Set<ConstraintViolation<DepartmentEntity>> violations = validator.validate(dept);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("departmentId")));
    }

    @Test
    @DisplayName("Blank department name should fail @NotBlank")
    void blankDepartmentName_shouldViolate() {
        DepartmentEntity dept = validDepartment();
        dept.setName("");
        Set<ConstraintViolation<DepartmentEntity>> violations = validator.validate(dept);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Null department name should fail @NotBlank")
    void nullDepartmentName_shouldViolate() {
        DepartmentEntity dept = validDepartment();
        dept.setName(null);
        Set<ConstraintViolation<DepartmentEntity>> violations = validator.validate(dept);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Null head physician should fail @NotNull")
    void nullDepartmentHead_shouldViolate() {
        DepartmentEntity dept = validDepartment();
        dept.setHead(null);
        Set<ConstraintViolation<DepartmentEntity>> violations = validator.validate(dept);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("head")));
    }
}
