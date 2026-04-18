package com.sprint.project.patientAppointment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.patientAppointment.Entity.PatientEntity;
import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

	    Optional<PatientEntity> findBySsn(Integer ssn);
}