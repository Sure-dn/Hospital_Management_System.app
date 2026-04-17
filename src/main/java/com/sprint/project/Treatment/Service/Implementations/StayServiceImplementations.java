package com.sprint.project.Treatment.Service.Implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.Entity.StayEntity;
import com.sprint.project.Treatment.Repository.StayRepository;
import com.sprint.project.Treatment.Service.StayService;
import com.sprint.project.exception.BadRequestException;
@Service
public class StayServiceImplementations implements StayService {
	@Autowired
	private StayRepository stayRepository;
	@Override
	public StayEntity admitPatient(StayEntity stay) {
		if(stay.getStayStart().isAfter(LocalDateTime.now())) {
			throw new BadRequestException("Stay start cannot be in future");
		}
		
		return stayRepository.save(stay);
	}
	
	@Override
	public StayEntity getStayById(Integer stayId) {
		return stayRepository.findById(stayId).
				orElseThrow(() -> new RuntimeException("Stay not found"));
	}
	
	@Override
	public List<StayEntity> getAllStays(){
		return stayRepository.findAll();
	}
	
	@Override
	public StayEntity dischargePatient(Integer stayId) {
		StayEntity stay = getStayById(stayId);
		stay.setStayEnd(LocalDateTime.now());
		return stayRepository.save(stay);
	}
}
