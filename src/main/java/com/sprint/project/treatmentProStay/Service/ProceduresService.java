package com.sprint.project.treatmentprostay.service;

import java.util.List;

import com.sprint.project.treatmentprostay.DTO.ProceduresRequestDTO;
import com.sprint.project.treatmentprostay.entity.ProceduresEntity;

public interface ProceduresService {
	ProceduresRequestDTO addProcedure(ProceduresRequestDTO p);
	List<ProceduresRequestDTO> getAllProcedures();
	ProceduresRequestDTO getProcedureById(Integer code);
	ProceduresRequestDTO updateProcedure(Integer code, ProceduresRequestDTO procedure);
	ProceduresRequestDTO deleteProcedure(Integer code);
	List<ProceduresRequestDTO> searchByName(String name);
	List<ProceduresRequestDTO> getByCostRange(Double min, Double max);
	List<ProceduresRequestDTO> getExpensiveProcedures(Double cost);
	List<ProceduresRequestDTO> sortByCost();
}
