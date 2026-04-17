package com.sprint.project.Treatment.Service;

import java.util.List;

import com.sprint.project.Treatment.Entity.ProceduresEntity;

public interface ProceduresService {
	ProceduresEntity addProcedure(ProceduresEntity procedure);
	List<ProceduresEntity> getAllProcedures();
	ProceduresEntity getProcedureById(Integer code);
	ProceduresEntity updateProcedure(Integer code, ProceduresEntity procedure);
	ProceduresEntity deleteProcedure(Integer code);
	List<ProceduresEntity> searchByName(String name);
	List<ProceduresEntity> getByCostRange(Double min, Double max);
	List<ProceduresEntity> getExpensiveProcedures(Double cost);
	List<ProceduresEntity> sortByCost();
}
