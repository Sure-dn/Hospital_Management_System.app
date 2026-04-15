package com.sprint.project.NurseOnCallRoomAPIs.Repository;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


   

  
}
