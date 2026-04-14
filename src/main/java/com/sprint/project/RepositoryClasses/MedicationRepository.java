package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Integer>{

}
