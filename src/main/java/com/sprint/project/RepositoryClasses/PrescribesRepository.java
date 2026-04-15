package com.sprint.project.RepositoryClasses;

import com.sprint.project.EntityClasses.Prescribes;
import com.sprint.project.EntityClasses.PrescribesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PrescribesRepository extends JpaRepository<Prescribes, PrescribesId> {

   

}