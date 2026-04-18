package com.sprint.project.NurseOnCallRoomAPIs.Repository;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.RoomEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

	Optional<PatientEntity> findById(RoomEntity room);


   

  
}
