package com.sprint.project.patientAppointment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.patientAppointment.Entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

}
