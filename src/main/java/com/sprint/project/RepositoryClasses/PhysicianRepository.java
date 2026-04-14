package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

}
