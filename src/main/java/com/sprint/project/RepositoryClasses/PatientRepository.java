package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.EntityClasses.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>  {

}
