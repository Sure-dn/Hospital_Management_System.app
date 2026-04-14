package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.AffiliatedId;
import com.sprint.project.EntityClasses.Affiliated_With;

public interface Affiliated_WithRepository extends JpaRepository<Affiliated_With, AffiliatedId> {

}
