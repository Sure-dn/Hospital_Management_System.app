package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.OnCallResponseDTO;
import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.nurseoncallroom.entity.OnCallEntity;
import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.nurseoncallroom.repository.NurseRepository;
import com.sprint.project.nurseoncallroom.repository.OnCallRepository;
import com.sprint.project.nurseoncallroom.service.OnCallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OnCallServiceImpl implements OnCallService {

    @Autowired
    private OnCallRepository onCallRepository;

    @Autowired
    private NurseRepository nurseRepository;

    // ==============================
    // 🔹 ENTITY → DTO CONVERSION
    // ==============================
    private OnCallResponseDTO toDTO(OnCallEntity e) {
        OnCallResponseDTO dto = new OnCallResponseDTO();
        dto.setNurseEmployeeId(e.getNurse().getEmployeeId());
        dto.setNurseName(e.getNurse().getName());
        dto.setBlockFloor(e.getBlockFloor());
        dto.setBlockCode(e.getBlockCode());
        dto.setOnCallStart(e.getOnCallStart());
        dto.setOnCallEnd(e.getOnCallEnd());
        return dto;
    }

    // ==============================
    // 🚀 CREATE ON-CALL SHIFT
    // ==============================
    @Override
    public OnCallResponseDTO assignOnCall(Integer employeeId, OnCallRequestDTO request) {

        // 🔹 Fetch Nurse
        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new NurseNotAvailableException(employeeId));

        // 🔴 1. TIME VALIDATION
        // 🔴 1. NULL CHECK (ADD THIS FIRST)
        if (request.getOnCallStart() == null || request.getOnCallEnd() == null) {
            throw new InvalidShiftTimeException("Start or End time cannot be null");
        }

// 🔴 2. TIME VALIDATION
        if (request.getOnCallEnd().isBefore(request.getOnCallStart())) {
            throw new InvalidShiftTimeException("End time must be after start time");
        }

        // 🔴 2. DUPLICATE CHECK
        boolean duplicate = onCallRepository.existsByNurseAndOnCallStartAndOnCallEnd(
                nurse,
                request.getOnCallStart(),
                request.getOnCallEnd()
        );

        if (duplicate) {
            // ✅ matches Integer constructor
            throw new NurseAlreadyAssignedException(employeeId);
        }

        // 🔴 3. OVERLAP CHECK
        boolean overlap = onCallRepository.existsOverlap(
                nurse,
                request.getOnCallStart(),
                request.getOnCallEnd()
        );

        if (overlap) {
            // ✅ matches String constructor
            throw new OnCallScheduleConflictException(
                    "Nurse already has a schedule conflict between "
                            + request.getOnCallStart() + " and " + request.getOnCallEnd()
            );
        }

        // 🔹 SAVE ENTITY
        OnCallEntity entity = new OnCallEntity();
        entity.setNurse(nurse);
        entity.setBlockFloor(request.getBlockFloor());
        entity.setBlockCode(request.getBlockCode());
        entity.setOnCallStart(request.getOnCallStart());
        entity.setOnCallEnd(request.getOnCallEnd());

        OnCallEntity saved = onCallRepository.save(entity);

        return toDTO(saved);
    }

    // ==============================
    // ✅ GET BY NURSE
    // ==============================
    @Override
    @Transactional(readOnly = true)
    public List<OnCallResponseDTO> getOnCallByNurse(Integer employeeId) {

        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new NurseNotAvailableException(employeeId));

        List<OnCallEntity> list = onCallRepository.findByNurse(nurse);


        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==============================
    // ✅ GET BY BLOCK
    // ==============================
    @Override
    @Transactional(readOnly = true)
    public List<OnCallResponseDTO> getOnCallByBlock(Integer floor, Integer code) {

        List<OnCallEntity> list = onCallRepository.findByBlockFloorAndBlockCode(floor, code);

        if (list.isEmpty()) {
            throw new BlockNotAvailableException(floor, code);
        }

        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==============================
    // ✅ DELETE
    // ==============================
    @Override
    public void deleteOnCall(Integer employeeId) {

        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new NurseNotAvailableException(employeeId));

        onCallRepository.deleteByNurse(nurse);
    }
}