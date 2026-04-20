package com.sprint.project.medicationprescription;

import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.entity.PrescribesEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MedicationEntity and PrescribesEntity validations.
 */
class MedicationAndPrescribesValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ========================
    // MedicationEntity Tests
    // ========================

    private MedicationEntity validMedication() {
        MedicationEntity med = new MedicationEntity();
        med.setCode(1001);
        med.setName("Ibuprofen");
        med.setBrand("Advil");
        med.setDescription("Pain reliever and fever reducer");
        return med;
    }

    @Test
    @DisplayName("Valid MedicationEntity should have no violations")
    void validMedication_noViolations() {
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(validMedication());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null code should fail @NotNull")
    void nullCode_shouldViolate() {
        MedicationEntity med = validMedication();
        med.setCode(null);
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(med);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("code")));
    }

    @Test
    @DisplayName("Blank name should fail @NotBlank")
    void blankMedicationName_shouldViolate() {
        MedicationEntity med = validMedication();
        med.setName("");
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(med);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Null name should fail @NotBlank")
    void nullMedicationName_shouldViolate() {
        MedicationEntity med = validMedication();
        med.setName(null);
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(med);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Blank brand should fail @NotBlank")
    void blankBrand_shouldViolate() {
        MedicationEntity med = validMedication();
        med.setBrand("   ");
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(med);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("brand")));
    }

    @Test
    @DisplayName("Blank description should fail @NotBlank")
    void blankDescription_shouldViolate() {
        MedicationEntity med = validMedication();
        med.setDescription("");
        Set<ConstraintViolation<MedicationEntity>> violations = validator.validate(med);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
    }

    // ========================
    // PrescribesEntity Tests
    // ========================

    private PrescribesEntity validPrescribes() {
        PhysicianEntity physician = new PhysicianEntity();
        physician.setEmployeeId(1);
        physician.setName("Dr. Smith");
        physician.setPosition("Cardiologist");
        physician.setSsn(111222333);

        PatientEntity patient = new PatientEntity();
        patient.setSsn(987654321);
        patient.setName("Jane Doe");
        patient.setAddress("123 Main St");
        patient.setPhone("555-1234");
        patient.setInsuranceId(200);
        patient.setPcp(1);

        MedicationEntity medication = validMedication();

        PrescribesEntity prescribes = new PrescribesEntity();
        prescribes.setPhysician(physician);
        prescribes.setPatient(patient);
        prescribes.setMedication(medication);
        prescribes.setDate(LocalDateTime.now());
        prescribes.setDose("500mg");
        return prescribes;
    }

    @Test
    @DisplayName("Valid PrescribesEntity should have no violations")
    void validPrescribes_noViolations() {
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(validPrescribes());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null physician should fail @NotNull")
    void nullPhysician_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setPhysician(null);
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("physician")));
    }

    @Test
    @DisplayName("Null patient should fail @NotNull")
    void nullPatient_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setPatient(null);
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("patient")));
    }

    @Test
    @DisplayName("Null medication should fail @NotNull")
    void nullMedication_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setMedication(null);
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("medication")));
    }

    @Test
    @DisplayName("Null date should fail @NotNull")
    void nullDate_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setDate(null);
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("date")));
    }

    @Test
    @DisplayName("Blank dose should fail @NotBlank")
    void blankDose_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setDose("  ");
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dose")));
    }

    @Test
    @DisplayName("Dose longer than 50 chars should fail @Size")
    void longDose_shouldViolate() {
        PrescribesEntity p = validPrescribes();
        p.setDose("X".repeat(51));
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dose")));
    }

    @Test
    @DisplayName("Dose exactly 50 chars should pass @Size")
    void maxDose_shouldPass() {
        PrescribesEntity p = validPrescribes();
        p.setDose("A".repeat(50));
        Set<ConstraintViolation<PrescribesEntity>> violations = validator.validate(p);
        assertTrue(violations.isEmpty());
    }
}
