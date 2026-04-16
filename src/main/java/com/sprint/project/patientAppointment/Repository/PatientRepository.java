package com.sprint.project.patientAppointment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.patientAppointment.Entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

	    Optional<PatientEntity> findBySsn(Integer ssn);

	    boolean existsBySsn(Integer ssn);
	}

