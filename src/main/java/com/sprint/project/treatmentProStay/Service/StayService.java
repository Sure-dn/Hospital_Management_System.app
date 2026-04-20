package com.sprint.project.treatmentprostay.service;

import java.util.List;

import com.sprint.project.treatmentprostay.DTO.StayRequestDTO;
import com.sprint.project.treatmentprostay.entity.StayEntity;

public interface StayService {

    StayEntity admitPatient(StayRequestDTO dto);

    StayEntity getStayById(Integer stayId);

    List<StayEntity> getAllStays();

    StayEntity dischargePatient(Integer stayId);

	String deleteStay(int id);
}