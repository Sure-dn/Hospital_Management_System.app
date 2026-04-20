package com.sprint.project.patientAppointment.Repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    Optional<AppointmentEntity> findByAppointmentId(Integer id);

    List<AppointmentEntity> findByPatient_Ssn(Integer ssn);

    List<AppointmentEntity> findByPhysician_EmployeeId(Integer id);

    // DATE QUERY
    @Query("SELECT a FROM AppointmentEntity a WHERE FUNCTION('DATE', a.starttime) = :date")
    List<AppointmentEntity> findByDate(@Param("date") LocalDate date);

    // RANGE QUERY
    @Query("SELECT a FROM AppointmentEntity a WHERE a.patient.ssn = :ssn AND a.starttime >= :from AND a.starttime <= :to")
    List<AppointmentEntity> findByPatientAndDateRange(
            @Param("ssn") Integer ssn,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
           "FROM AppointmentEntity a " +
           "WHERE a.physician.employeeId = :physicianId " +
           "AND (a.starttime < :endTime AND a.endtime > :startTime)")
    boolean existsOverlappingAppointmentForPhysician(
            @Param("physicianId") Integer physicianId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    boolean existsByAppointmentId(Integer appointmentId);
}