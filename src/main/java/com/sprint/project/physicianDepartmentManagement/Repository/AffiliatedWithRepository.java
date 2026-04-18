package com.sprint.project.physicianDepartmentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;


public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWithEntity, AffiliatedId> {
////	boolean existsByAffiliatedId(AffiliatedId affiliatedId);
//
////    List<AffiliatedWithEntity> findByPhysician_PhysicianId(Integer physicianId);
////
////    List<AffiliatedWithEntity> findByDepartment_DepartmentId(Integer departmentId);
////    
////    // Custom: Get active affiliations (example business rule)
////    @Query("SELECT a FROM AffiliatedWithEntity a WHERE a.active = true")
////    List<AffiliatedWithEntity> findActiveAffiliations();
//	 // Check duplicate affiliation
//  
//
//    // Get all affiliations by physician ID
////    List<AffiliatedWithEntity> findByPhysicianEmployeeID(Integer physicianId);
////
////    // Get all physicians in a department
////    List<AffiliatedWithEntity> findByDepartmentDepartmentId(Integer departmentId);
	boolean existsByAffiliatedId(AffiliatedId id);

    // ✔ Get all affiliations of a physician
    List<AffiliatedWithEntity> findByPhysicianEmployeeId(Integer physicianId);

    // ✔ Get all affiliations of a department
    List<AffiliatedWithEntity> findByDepartmentDepartmentId(Integer departmentId);
}
	


