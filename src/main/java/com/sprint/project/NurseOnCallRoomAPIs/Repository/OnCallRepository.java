package com.sprint.project.NurseOnCallRoomAPIs.Repository;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.OnCall;
import com.sprint.project.NurseOnCallRoomAPIs.Entity.OnCallId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface OnCallRepository extends JpaRepository<OnCall, OnCallId> {

  
}
