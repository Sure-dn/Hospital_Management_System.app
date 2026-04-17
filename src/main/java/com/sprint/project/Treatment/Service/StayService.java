package com.sprint.project.Treatment.Service;

import java.util.List;

import com.sprint.project.Treatment.Entity.StayEntity;

public interface StayService {
	StayEntity admitPatient(StayEntity stay);
	StayEntity getStayById(Integer stayId);
	List<StayEntity> getAllStays();
	StayEntity dischargePatient(Integer stayId);
}
