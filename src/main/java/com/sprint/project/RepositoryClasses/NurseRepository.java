package com.sprint.project.RepositoryClasses;

import com.sprint.project.EntityClasses.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> {
}