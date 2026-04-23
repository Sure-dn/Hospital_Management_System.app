package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.OnCallResponseDTO;
import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.nurseoncallroom.entity.OnCallEntity;
import com.sprint.project.nurseoncallroom.exception.OnCallNotFoundException;
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

    // ✅ Convert Entity → DTO
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

    // ✅ CREATE On-Call Shift
    @Override
    public OnCallResponseDTO assignOnCall(Integer employeeId, OnCallRequestDTO request) {

        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found with ID: " + employeeId));

        // 🔥 Optional: Prevent overlapping shifts
        boolean isOverlapping = onCallRepository.existsOverlappingShift(
                nurse,
                request.getBlockFloor(),
                request.getBlockCode(),
                request.getOnCallStart(),
                request.getOnCallEnd()
        );

        if (isOverlapping) {
            throw new RuntimeException("Overlapping on-call shift exists for this nurse");
        }

        OnCallEntity entity = new OnCallEntity();
        entity.setNurse(nurse);
        entity.setBlockFloor(request.getBlockFloor());
        entity.setBlockCode(request.getBlockCode());
        entity.setOnCallStart(request.getOnCallStart());
        entity.setOnCallEnd(request.getOnCallEnd());

        OnCallEntity saved = onCallRepository.save(entity);

        return toDTO(saved);
    }

    // ✅ GET On-Call by Nurse
    @Override
    @Transactional(readOnly = true)
    public List<OnCallResponseDTO> getOnCallByNurse(Integer employeeId) {

        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found with ID: " + employeeId));

        List<OnCallEntity> list = onCallRepository.findByNurse(nurse);

        if (list.isEmpty()) {
            throw new OnCallNotFoundException("No on-call shifts found for nurse ID: " + employeeId);
        }

        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET On-Call by Block (🔥 FIXED METHOD)
    @Override
    @Transactional(readOnly = true)
    public List<OnCallResponseDTO> getOnCallByBlock(Integer floor, Integer code) {

        List<OnCallEntity> list = onCallRepository
                .findByBlockFloorAndBlockCode(floor, code);

        if (list.isEmpty()) {
            throw new OnCallNotFoundException(
                    "No on-call shifts found for block floor: " + floor + " and code: " + code
            );
        }

        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ DELETE On-Call by Nurse
    @Override
    public void deleteOnCall(Integer employeeId) {

        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found with ID: " + employeeId));

        onCallRepository.deleteByNurse(nurse);
    }
}