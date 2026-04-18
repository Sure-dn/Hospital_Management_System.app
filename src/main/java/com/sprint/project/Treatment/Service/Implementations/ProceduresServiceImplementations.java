package com.sprint.project.Treatment.Service.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.DTO.ProceduresRequestDTO;
import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.Treatment.Service.ProceduresService;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;

@Service
public class ProceduresServiceImplementations implements ProceduresService {

    @Autowired
    private ProceduresRepository proceduresRepository;

    // 🔁 DTO → ENTITY
    private ProceduresEntity mapToEntity(ProceduresRequestDTO dto) {
        ProceduresEntity entity = new ProceduresEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());
        return entity;
    }

    // 🔁 ENTITY → DTO
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
            throw new BadRequestException("Code cannot be null");
        }

        if (proceduresRepository.existsById(dto.getCode())) {
            throw new DuplicateResourceException("Procedure already exists");
        }

        ProceduresEntity saved = proceduresRepository.save(mapToEntity(dto));

        return mapToDTO(saved);
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
                .orElseThrow(() -> new ResourceNotFoundException("Procedure not found"));

        return mapToDTO(entity);
    }

    @Override
    public ProceduresRequestDTO updateProcedure(Integer code, ProceduresRequestDTO dto) {

        ProceduresEntity existing = proceduresRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Procedure not found"));

        existing.setName(dto.getName());
        existing.setCost(dto.getCost());

        return mapToDTO(proceduresRepository.save(existing));
    }

    @Override
    public ProceduresRequestDTO deleteProcedure(Integer code) {

        ProceduresEntity existing = proceduresRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Procedure not found"));

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