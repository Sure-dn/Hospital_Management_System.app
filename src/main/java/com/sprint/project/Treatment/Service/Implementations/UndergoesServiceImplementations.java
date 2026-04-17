package com.sprint.project.Treatment.Service.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.Entity.UndergoesEntity;
import com.sprint.project.Treatment.Entity.UndergoesId;
import com.sprint.project.Treatment.Repository.UndergoesRepository;
import com.sprint.project.Treatment.Service.UndergoesService;
import com.sprint.project.exception.BadRequestException;
@Service
public class UndergoesServiceImplementations implements UndergoesService{
	@Autowired
	private UndergoesRepository undergoesRepository;
	@Override
	public UndergoesEntity assignTreatment(UndergoesEntity undergoes) {
		//Rule 1
		//Patient must match Stay patient
		if(!undergoes.getStay().getPatient().getSsn().equals(undergoes.getPatient().getSsn())) {
			throw new BadRequestException("patient mismatch with stay");
		}
		
		//Rule2
		//Procedure already exist done by fk
		
		//Rule3
		//Avoid duplicate treatment
		
		if(undergoesRepository.existsById(undergoes.getUndergoesId())) {
			throw new BadRequestException("Treatment already exists");
		}
		return undergoesRepository.save(undergoes);	
	}
	
	@Override
	public List<UndergoesEntity> getAllTreatments(){
		return undergoesRepository.findAll();
	}
	
	@Override
	public List<UndergoesEntity> getTreatmentByPatient(Integer patientId){
		return undergoesRepository.findAll()
				.stream()
				.filter(u->u.getPatient().getSsn().equals(patientId))
				.collect(Collectors.toList());
	}
	@Override
	public List<UndergoesEntity> getTreatmentByStay(Integer stayId){
		return undergoesRepository.findAll()
				.stream()
				.filter(u -> u.getStay().getStayId().equals(stayId))
				.collect(Collectors.toList());
	}
	
	@Override
	public UndergoesEntity deleteTreatment(UndergoesId id) {

	    UndergoesEntity existing = undergoesRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Treatment not found"));

	    undergoesRepository.delete(existing);

	    return existing;
	}
}
