package com.sprint.project.physicianDepartmentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.Entity.AffiliatedWithEntity;

public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWithEntity, AffiliatedId> {

}
