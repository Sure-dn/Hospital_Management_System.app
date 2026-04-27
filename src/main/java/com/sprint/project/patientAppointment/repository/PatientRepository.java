package com.sprint.project.patientAppointment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.patientAppointment.entity.PatientEntity;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

	    Optional<PatientEntity> findBySsn(Integer ssn);
	    boolean existsBySsn(Integer ssn);
	    
	    List<PatientEntity> findByPcp(Integer pcpPhysicianId);
	    
	    List<PatientEntity> findByNameContainingIgnoreCase(String name);
	    @Query(value = "SELECT * FROM patient WHERE ssn = :ssn", nativeQuery = true)
	    Optional<PatientEntity> findPatientNative(@Param("ssn") Integer ssn);
	    
	    @Query("SELECT p FROM PatientEntity p WHERE p.pcp= :physicianId")
	    List<PatientEntity> findPatientsByPcp(@Param("physicianId") Integer physicianId);
	    
	    @Modifying
	    @Query("UPDATE PatientEntity p SET p.pcp= :newPcpId WHERE p.ssn = :ssn")
	    int updatePcp(@Param("ssn") Integer ssn, @Param("newPcpId") Integer newPcpId);
}

