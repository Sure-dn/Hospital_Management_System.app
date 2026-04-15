package com.sprint.project.physicianDepartmentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInId;

public interface TrainedInRepository  extends JpaRepository<TrainedInEntity, TrainedInId>  {

}
