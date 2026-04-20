package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.request.NurseRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.NurseResponseDTO;
import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.nurseoncallroom.exception.BlockDuplicateResourceException;
import com.sprint.project.nurseoncallroom.exception.OnCallNotFoundException;
import com.sprint.project.nurseoncallroom.repository.NurseRepository;
import com.sprint.project.nurseoncallroom.service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    private NurseEntity toEntity(NurseRequestDTO dto) {
        NurseEntity e = new NurseEntity();
        e.setEmployeeId(dto.getEmployeeId());
        e.setName(dto.getName());
        e.setPosition(dto.getPosition());
        e.setRegistered(dto.getRegistered());
        e.setSsn(dto.getSsn());
        return e;
    }

    private NurseResponseDTO toDTO(NurseEntity e) {
        NurseResponseDTO dto = new NurseResponseDTO();
        dto.setEmployeeId(e.getEmployeeId());
        dto.setName(e.getName());
        dto.setPosition(e.getPosition());
        dto.setRegistered(e.getRegistered());
        dto.setSsn(e.getSsn());
        return dto;
    }

    @Override
    public NurseResponseDTO createNurse(NurseRequestDTO request) {
        if (nurseRepository.existsById(request.getEmployeeId()))
            throw new BlockDuplicateResourceException("Nurse already exists");
        return toDTO(nurseRepository.save(toEntity(request)));
    }

    @Override
    public List<NurseResponseDTO> getAllNurses() {
        return nurseRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public NurseResponseDTO getNurseById(Integer employeeId) {
        return toDTO(nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found")));
    }

    @Override
    public NurseResponseDTO updateNurse(Integer employeeId, NurseRequestDTO request) {
        NurseEntity existing = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found"));

        existing.setName(request.getName());
        existing.setPosition(request.getPosition());
        existing.setRegistered(request.getRegistered());

        return toDTO(nurseRepository.save(existing));
    }
}