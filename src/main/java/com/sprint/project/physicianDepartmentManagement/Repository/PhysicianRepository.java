package com.sprint.project.physicianDepartmentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

public interface PhysicianRepository extends JpaRepository<PhysicianEntity, Integer> {

}
