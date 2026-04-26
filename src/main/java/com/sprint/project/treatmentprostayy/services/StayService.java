package com.sprint.project.treatmentprostayy.services;

import java.util.List;

import com.sprint.project.treatmentprostayy.dto.StayRequestDTO;
import com.sprint.project.treatmentprostayy.entities.StayEntity;

public interface StayService {

    StayEntity admitPatient(StayRequestDTO dto);

    StayEntity getStayById(Integer stayId);

    List<StayEntity> getAllStays();

    StayEntity dischargePatient(Integer stayId);

	String deleteStay(int id);
}