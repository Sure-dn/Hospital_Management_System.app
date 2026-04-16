package com.sprint.project.Treatment.Service.Implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.Treatment.Service.ProceduresService;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
@Service
public class ProceduresServiceImplementations implements ProceduresService{
	@Autowired
	private ProceduresRepository proceduresRepository;
	
	@Override
	public ProceduresEntity addProcedure(ProceduresEntity procedure) {
		if (procedure.getCode() == null) {
	        throw new BadRequestException("Code cannot be null");
	    }
		if(proceduresRepository.existsById(procedure.getCode())) {
			throw new DuplicateResourceException("Procedure already exists with code: " + procedure.getCode());
		}
		try {
		    return proceduresRepository.save(procedure);
		} catch (Exception e) {
		    throw new DuplicateResourceException("Duplicate procedure detected");
		}
	}
	
	@Override
	public List<ProceduresEntity> getAllProcedures(){
		return proceduresRepository.findAll();
	}
	@Override
	public ProceduresEntity getProcedureById(Integer code) {
		return proceduresRepository.findById(code)
			    .orElseThrow(() -> new ResourceNotFoundException("Procedure not found with code: " + code));
	}
	
	@Override
	public ProceduresEntity updateProcedure(Integer code, ProceduresEntity procedure) {
		ProceduresEntity existing = getProcedureById(code);
		existing.setName(procedure.getName());
		existing.setCost(procedure.getCost());
		
		return proceduresRepository.save(existing);
		
	}
	
	public void deleteProcedure(Integer code) {
		if(!proceduresRepository.existsById(code)) {
			throw new RuntimeException("Cannot delete, procedure not found");
		}
		
		proceduresRepository.deleteById(code);
	}
}
