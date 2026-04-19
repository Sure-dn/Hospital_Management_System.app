package com.sprint.project.nurseOnCallRoomAPIs.service;

import com.sprint.project.nurseOnCallRoomAPIs.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseOnCallRoomAPIs.dto.response.OnCallResponseDTO;

import java.util.List;

public interface OnCallService {

    // ✅ CREATE
    OnCallResponseDTO assignOnCall(Integer employeeId, OnCallRequestDTO request);

    // ✅ GET by nurse
    List<OnCallResponseDTO> getOnCallByNurse(Integer employeeId);

    // ✅ GET by block
    List<OnCallResponseDTO> getOnCallByBlock(Integer floor, Integer code);

    // ✅ DELETE
    void deleteOnCall(Integer employeeId);
}