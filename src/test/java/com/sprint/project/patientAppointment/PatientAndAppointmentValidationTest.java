package com.sprint.project.patientAppointment;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
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
 * Unit tests for PatientEntity and AppointmentEntity validations.
 */
class PatientAndAppointmentValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ========================
    // PatientEntity Tests
    // ========================

    private PatientEntity validPatient() {
        PatientEntity p = new PatientEntity();
        p.setSsn(100200300);
        p.setName("Alice Brown");
        p.setAddress("456 Oak Avenue, Springfield");
        p.setPhone("555-9876");
        p.setInsuranceId(300);
        p.setPcp(5);
        return p;
    }

    @Test
    @DisplayName("Valid PatientEntity should have no violations")
    void validPatient_noViolations() {
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(validPatient());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null SSN should fail @NotNull")
    void nullPatientSsn_shouldViolate() {
        PatientEntity p = validPatient();
        p.setSsn(null);
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("ssn")));
    }

    @Test
    @DisplayName("Blank patient name should fail @NotBlank")
    void blankPatientName_shouldViolate() {
        PatientEntity p = validPatient();
        p.setName("");
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    @DisplayName("Null patient name should fail @NotBlank")
    void nullPatientName_shouldViolate() {
        PatientEntity p = validPatient();
        p.setName(null);
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Blank address should fail @NotBlank")
    void blankAddress_shouldViolate() {
        PatientEntity p = validPatient();
        p.setAddress("   ");
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("address")));
    }

    @Test
    @DisplayName("Blank phone should fail @NotBlank")
    void blankPhone_shouldViolate() {
        PatientEntity p = validPatient();
        p.setPhone("");
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phone")));
    }

    @Test
    @DisplayName("Null insuranceId should fail @NotNull")
    void nullInsuranceId_shouldViolate() {
        PatientEntity p = validPatient();
        p.setInsuranceId(null);
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("insuranceId")));
    }

    @Test
    @DisplayName("Null pcp should fail @NotNull")
    void nullPcp_shouldViolate() {
        PatientEntity p = validPatient();
        p.setPcp(null);
        Set<ConstraintViolation<PatientEntity>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("pcp")));
    }

    // ========================
    // AppointmentEntity Tests
    // ========================

    private AppointmentEntity validAppointment() {
        PatientEntity patient = validPatient();

        PhysicianEntity physician = new PhysicianEntity();
        physician.setEmployeeId(10);
        physician.setName("Dr. Jones");
        physician.setPosition("Surgeon");
        physician.setSsn(444555666);

        NurseEntity nurse = new NurseEntity(201, "Nurse Betty", "Senior Nurse", true, 777888999);

        AppointmentEntity appt = new AppointmentEntity();
        appt.setAppointmentId(5001);
        appt.setPatient(patient);
        appt.setPhysician(physician);
        appt.setPrepNurse(nurse);
        appt.setStart(LocalDateTime.of(2025, 6, 15, 9, 0));
        appt.setEnd(LocalDateTime.of(2025, 6, 15, 10, 0));
        appt.setExaminationRoom("A3");
        return appt;
    }

    @Test
    @DisplayName("Valid AppointmentEntity should have no violations")
    void validAppointment_noViolations() {
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(validAppointment());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Null appointmentId should fail @NotNull")
    void nullAppointmentId_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setAppointmentId(null);
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("appointmentId")));
    }

    @Test
    @DisplayName("Null patient should fail @NotNull")
    void nullPatientInAppointment_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setPatient(null);
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("patient")));
    }

    @Test
    @DisplayName("Null physician should fail @NotNull")
    void nullPhysicianInAppointment_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setPhysician(null);
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("physician")));
    }

    @Test
    @DisplayName("Null starttime should fail @NotNull")
    void nullStarttime_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setStart(null);
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("starttime")));
    }

    @Test
    @DisplayName("Null endtime should fail @NotNull")
    void nullEndtime_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setEnd(null);
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("endtime")));
    }

    @Test
    @DisplayName("Blank examinationRoom should fail @NotBlank")
    void blankExaminationRoom_shouldViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setExaminationRoom("  ");
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("examinationRoom")));
    }

    @Test
    @DisplayName("PrepNurse is optional - null should not cause violation")
    void nullPrepNurse_shouldNotViolate() {
        AppointmentEntity appt = validAppointment();
        appt.setPrepNurse(null);  // PrepNurse is not annotated @NotNull
        Set<ConstraintViolation<AppointmentEntity>> violations = validator.validate(appt);
        assertTrue(violations.isEmpty(), "PrepNurse is optional and null should be allowed");
    }
}
