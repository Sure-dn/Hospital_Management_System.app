package com.sprint.project.Treatment.Service;

import java.util.List;

import com.sprint.project.Treatment.Entity.ProceduresEntity;

public interface ProceduresService {
	ProceduresEntity addProcedure(ProceduresEntity procedure);
	List<ProceduresEntity> getAllProcedures();
	ProceduresEntity getProcedureById(Integer code);
	ProceduresEntity updateProcedure(Integer code, ProceduresEntity procedure);
	void deleteProcedure(Integer code);
}
