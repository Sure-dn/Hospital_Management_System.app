package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.EntityClasses.Undergoes;
import com.sprint.project.EntityClasses.UndergoesId;

public interface UndergoesRepository extends JpaRepository<Undergoes, UndergoesId> {
}