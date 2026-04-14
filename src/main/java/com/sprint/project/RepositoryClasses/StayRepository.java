package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.EntityClasses.Stay;

public interface StayRepository extends JpaRepository<Stay, Integer> {
}