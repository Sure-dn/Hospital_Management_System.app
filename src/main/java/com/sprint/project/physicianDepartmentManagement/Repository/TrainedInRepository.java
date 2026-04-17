package com.sprint.project.physicianDepartmentManagement.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInId;

public interface TrainedInRepository  extends JpaRepository<TrainedInEntity, TrainedInId>  {
	 // Fetch all trainings for a physician
    List<TrainedInEntity> findByPhysician_EmployeeId(Integer employeeId);

    // Custom query for valid (non-expired) trainings
    @Query("SELECT t FROM TrainedInEntity t " +
           "WHERE t.physician.employeeId = :employeeId " +
           "AND t.certificationExpiry > :currentDate")
    List<TrainedInEntity> findValidTrainingsByPhysicianId(
            Integer employeeId, LocalDate currentDate);
    //new custom query for expiredtraining
    @Query("SELECT t FROM TrainedInEntity t " +
            "WHERE t.certificationExpiry <= :currentDate")
     List<TrainedInEntity> findExpiredTrainings(LocalDate currentDate);

}
