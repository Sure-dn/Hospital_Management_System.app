package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NurseEntity validations.
 * Tests all @NotNull, @NotBlank, and @Size constraints.
 */
class NurseEntityValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // -------------------------------------------------------
    // Helper
    // -------------------------------------------------------
    private NurseEntity validNurse() {
        return new NurseEntity(101, "John Doe", "Head Nurse", true, 123456789);
    }

    private long countViolationsForField(Set<ConstraintViolation<NurseEntity>> violations, String field) {
        return violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals(field))
                .count();
    }

    // -------------------------------------------------------
    // Valid entity – no violations expected
    // -------------------------------------------------------
    @Test
    @DisplayName("Valid NurseEntity should have no violations")
    void validNurse_noViolations() {
        NurseEntity nurse = validNurse();
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertTrue(violations.isEmpty(), "Expected no violations for a fully valid nurse");
    }

    // -------------------------------------------------------
    // employeeId – @NotNull
    // -------------------------------------------------------
    @Test
    @DisplayName("Null employeeId should fail @NotNull")
    void nullEmployeeId_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setEmployeeId(null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertEquals(1, countViolationsForField(violations, "employeeId"));
    }

    // -------------------------------------------------------
    // name – @NotBlank + @Size(min=3, max=50)
    // -------------------------------------------------------
    @Test
    @DisplayName("Blank name should fail @NotBlank")
    void blankName_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setName("   ");
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Null name should fail @NotBlank")
    void nullName_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setName(null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Name shorter than 3 chars should fail @Size")
    void shortName_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setName("Jo");
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Name longer than 50 chars should fail @Size")
    void longName_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setName("A".repeat(51));
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Name exactly 3 chars should pass @Size")
    void minLengthName_shouldPass() {
        NurseEntity nurse = validNurse();
        nurse.setName("Ana");
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Name exactly 50 chars should pass @Size")
    void maxLengthName_shouldPass() {
        NurseEntity nurse = validNurse();
        nurse.setName("A".repeat(50));
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertTrue(violations.isEmpty());
    }

    // -------------------------------------------------------
    // position – @NotBlank
    // -------------------------------------------------------
    @Test
    @DisplayName("Blank position should fail @NotBlank")
    void blankPosition_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setPosition("");
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("position")));
    }

    @Test
    @DisplayName("Null position should fail @NotBlank")
    void nullPosition_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setPosition(null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("position")));
    }

    // -------------------------------------------------------
    // registered – @NotNull
    // -------------------------------------------------------
    @Test
    @DisplayName("Null registered flag should fail @NotNull")
    void nullRegistered_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setRegistered(null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertEquals(1, countViolationsForField(violations, "registered"));
    }

    // -------------------------------------------------------
    // ssn – @NotNull
    // -------------------------------------------------------
    @Test
    @DisplayName("Null SSN should fail @NotNull")
    void nullSsn_shouldViolate() {
        NurseEntity nurse = validNurse();
        nurse.setSsn(null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        assertFalse(violations.isEmpty());
        assertEquals(1, countViolationsForField(violations, "ssn"));
    }

    // -------------------------------------------------------
    // Multiple violations at once
    // -------------------------------------------------------
    @Test
    @DisplayName("Multiple invalid fields should produce multiple violations")
    void multipleInvalidFields_shouldProduceManyViolations() {
        NurseEntity nurse = new NurseEntity(null, null, null, null, null);
        Set<ConstraintViolation<NurseEntity>> violations = validator.validate(nurse);
        // employeeId, name, position, registered, ssn all null → at least 5 violations
        assertTrue(violations.size() >= 5,
                "Expected at least 5 violations but got: " + violations.size());
    }
}
