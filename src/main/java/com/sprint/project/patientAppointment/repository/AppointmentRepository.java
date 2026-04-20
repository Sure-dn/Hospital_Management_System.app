package com.sprint.project.patientAppointment.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.patientAppointment.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    //  FIND BY APPOINTMENT ID
    // (Optional because JpaRepository already has findById)
    Optional<AppointmentEntity> findByAppointmentId(Integer appointmentId);

    //  FIND ALL APPOINTMENTS BY PATIENT SSN
    List<AppointmentEntity> findByPatient_Ssn(Integer ssn);

    //  FIND ALL APPOINTMENTS BY PHYSICIAN ID
    List<AppointmentEntity> findByPhysician_EmployeeId(Integer physicianId);

    //  FIND APPOINTMENTS BY DATE (IGNORE TIME PART)
    @Query("SELECT a FROM AppointmentEntity a " +
           "WHERE FUNCTION('DATE', a.starttime) = :date")
    List<AppointmentEntity> findByDate(@Param("date") LocalDate date);

    //  FIND APPOINTMENTS FOR PATIENT IN DATE RANGE
    @Query("SELECT a FROM AppointmentEntity a " +
           "WHERE a.patient.ssn = :ssn " +
           "AND a.starttime BETWEEN :from AND :to")
    List<AppointmentEntity> findByPatientAndDateRange(
            @Param("ssn") Integer ssn,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    //  BUSINESS RULE: CHECK OVERLAPPING APPOINTMENTS (IMPORTANT)
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
           "FROM AppointmentEntity a " +
           "WHERE a.physician.employeeId = :physicianId " +
           "AND (a.starttime < :endTime AND a.endtime > :startTime)")
    boolean existsOverlappingAppointment(
            @Param("physicianId") Integer physicianId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}