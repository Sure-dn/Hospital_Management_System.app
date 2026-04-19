package com.sprint.project.nurseOnCallRoomAPIs.service.implementation;

import com.sprint.project.nurseOnCallRoomAPIs.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseOnCallRoomAPIs.dto.response.OnCallResponseDTO;
import com.sprint.project.nurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.nurseOnCallRoomAPIs.entity.OnCallEntity;
import com.sprint.project.nurseOnCallRoomAPIs.exception.OnCallNotFoundException;
import com.sprint.project.nurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.nurseOnCallRoomAPIs.repository.OnCallRepository;
import com.sprint.project.nurseOnCallRoomAPIs.service.OnCallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OnCallServiceImpl implements OnCallService {

    @Autowired private OnCallRepository onCallRepository;
    @Autowired private NurseRepository nurseRepository;

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

    @Override
    public OnCallResponseDTO assignOnCall(Integer employeeId, OnCallRequestDTO request) {
        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found"));

        OnCallEntity entity = new OnCallEntity();
        entity.setNurse(nurse);
        entity.setBlockFloor(request.getBlockFloor());
        entity.setBlockCode(request.getBlockCode());
        entity.setOnCallStart(request.getOnCallStart());
        entity.setOnCallEnd(request.getOnCallEnd());

        return toDTO(onCallRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OnCallResponseDTO> getOnCallByNurse(Integer employeeId) {
        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found"));

        return onCallRepository.findByNurse(nurse)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OnCallResponseDTO> getOnCallByBlock(Integer floor, Integer code) {
        return List.of();
    }

    @Override
    public void deleteOnCall(Integer employeeId) {
        NurseEntity nurse = nurseRepository.findById(employeeId)
                .orElseThrow(() -> new OnCallNotFoundException("Nurse not found"));

        onCallRepository.deleteByNurse(nurse);
    }
}