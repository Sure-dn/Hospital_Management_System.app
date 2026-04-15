package com.sprint.project.NurseOnCallRoomAPIs.Repository;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, Integer> {

  

}
