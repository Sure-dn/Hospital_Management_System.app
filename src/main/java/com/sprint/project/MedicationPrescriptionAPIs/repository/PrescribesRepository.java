package com.sprint.project.MedicationPrescriptionAPIs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.MedicationPrescriptionAPIs.entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.entity.PrescribesId;

import java.time.LocalDateTime;
import java.util.List;

public interface PrescribesRepository extends JpaRepository<PrescribesEntity, PrescribesId> {

    // Custom Query 1: Prescriptions by Patient SSN
    @Query("SELECT p FROM PrescribesEntity p WHERE p.patient.ssn = :ssn")
    List<PrescribesEntity> findByPatientSsn(@Param("ssn") Integer ssn);

    // Custom Query 2: Prescriptions by Physician EmployeeID
    @Query("SELECT p FROM PrescribesEntity p WHERE p.physician.employeeId = :employeeId")
    List<PrescribesEntity> findByPhysicianEmployeeId(@Param("employeeId") Integer employeeId);

    // Custom Query 3: Prescriptions by Medication Code
    @Query("SELECT p FROM PrescribesEntity p WHERE p.medication.code = :code")
    List<PrescribesEntity> findByMedicationCode(@Param("code") Integer code);

	List<PrescribesEntity> findByDateBetween(LocalDateTime from, LocalDateTime to);
}