package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.EntityClasses.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {

}