package com.sprint.project.nurseoncallroom.service;

import com.sprint.project.nurseoncallroom.dto.request.NurseRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.NurseResponseDTO;

import java.util.List;

public interface NurseService {

    // ✅ CREATE
    NurseResponseDTO createNurse(NurseRequestDTO request);

    // ✅ GET ALL
    List<NurseResponseDTO> getAllNurses();

    // ✅ GET BY ID
    NurseResponseDTO getNurseById(Integer employeeId);

    // ✅ UPDATE
    NurseResponseDTO updateNurse(Integer employeeId, NurseRequestDTO request);
}