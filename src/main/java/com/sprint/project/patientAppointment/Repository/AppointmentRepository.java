package com.sprint.project.patientAppointment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
	boolean existsByAppointmentId(Integer id);

	Optional<AppointmentEntity> findByAppointmentId(Integer id);

	List<AppointmentEntity> findByPatient_Ssn(Integer ssn);

	List<AppointmentEntity> findByPhysician_EmployeeId(Integer id);

    @Query("SELECT a FROM AppointmentEntity a WHERE DATE(a.starttime) = :date")
    List<AppointmentEntity> findByDate(@Param("date") LocalDate date);

}

