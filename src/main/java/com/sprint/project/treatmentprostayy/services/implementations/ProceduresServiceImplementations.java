package com.sprint.project.treatmentprostayy.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.treatmentprostayy.dto.ProceduresRequestDTO;
import com.sprint.project.treatmentprostayy.entities.ProceduresEntity;
import com.sprint.project.treatmentprostayy.exception.InvalidProcedureException;
import com.sprint.project.treatmentprostayy.exception.ProcedureAlreadyExistsException;
import com.sprint.project.treatmentprostayy.exception.ProcedureNotFoundException;
import com.sprint.project.treatmentprostayy.repositories.ProceduresRepository;
import com.sprint.project.treatmentprostayy.services.ProceduresService;

@Service
public class ProceduresServiceImplementations implements ProceduresService {

    @Autowired
    private ProceduresRepository proceduresRepository;

    // 🔁 dto → ENTITY
    private ProceduresEntity mapToEntity(ProceduresRequestDTO dto) {
        ProceduresEntity entity = new ProceduresEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());
        return entity;
    }

    // 🔁 ENTITY → dto
    private ProceduresRequestDTO mapToDTO(ProceduresEntity entity) {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCost(entity.getCost());
        return dto;
    }

    @Override
    public ProceduresRequestDTO addProcedure(ProceduresRequestDTO dto) {

        if (dto.getCode() == null) {
            throw new InvalidProcedureException("Procedure code cannot be null");
        }

        if (proceduresRepository.existsById(dto.getCode())) {
            throw new ProcedureAlreadyExistsException("Procedure already exists");
        }

        return mapToDTO(proceduresRepository.save(mapToEntity(dto)));
    }

    @Override
    public List<ProceduresRequestDTO> getAllProcedures() {
        return proceduresRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProceduresRequestDTO getProcedureById(Integer code) {

        ProceduresEntity entity = proceduresRepository.findById(code)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found with id: " + code));

        return mapToDTO(entity);
    }

    @Override
    public ProceduresRequestDTO updateProcedure(Integer code, ProceduresRequestDTO dto) {

        ProceduresEntity existing = proceduresRepository.findById(code)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found"));

        existing.setName(dto.getName());
        existing.setCost(dto.getCost());

        return mapToDTO(proceduresRepository.save(existing));
    }

    @Override
    public ProceduresRequestDTO deleteProcedure(Integer code) {

        ProceduresEntity existing = proceduresRepository.findById(code)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found"));

        proceduresRepository.delete(existing);

        return mapToDTO(existing);
    }

    @Override
    public List<ProceduresRequestDTO> searchByName(String name) {
        return proceduresRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProceduresRequestDTO> getByCostRange(Double min, Double max) {
        return proceduresRepository.findByCostBetween(min, max)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProceduresRequestDTO> getExpensiveProcedures(Double cost) {
        return proceduresRepository.getExpensiveProcedures(cost)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProceduresRequestDTO> sortByCost() {
        return proceduresRepository.findAllByOrderByCostAsc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}