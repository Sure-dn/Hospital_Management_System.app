package com.sprint.project.Treatment.Service;

import java.util.List;

import com.sprint.project.Treatment.DTO.StayRequestDTO;
import com.sprint.project.Treatment.Entity.StayEntity;

public interface StayService {

    StayEntity admitPatient(StayRequestDTO dto);

    StayEntity getStayById(Integer stayId);

    List<StayEntity> getAllStays();

    StayEntity dischargePatient(Integer stayId);
}