package com.sprint.project.treatmentProStay.Service;

import java.util.List;

import com.sprint.project.treatmentProStay.DTO.StayRequestDTO;
import com.sprint.project.treatmentProStay.Entity.StayEntity;

public interface StayService {

    StayEntity admitPatient(StayRequestDTO dto);

    StayEntity getStayById(Integer stayId);

    List<StayEntity> getAllStays();

    StayEntity dischargePatient(Integer stayId);

	String deleteStay(int id);
}