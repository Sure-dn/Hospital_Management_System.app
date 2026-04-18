package com.sprint.project.NurseOnCallRoomAPIs.controller;

import com.sprint.project.NurseOnCallRoomAPIs.dto.ResponseStructure;
import com.sprint.project.NurseOnCallRoomAPIs.dto.request.NurseRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.NurseResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.service.NurseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    // POST /api/nurses
    @PostMapping
    public ResponseEntity<ResponseStructure<NurseResponseDTO>> createNurse(
            @Valid @RequestBody NurseRequestDTO request) {

        NurseResponseDTO responseDTO = nurseService.createNurse(request);

        ResponseStructure<NurseResponseDTO> response = ResponseStructure.success(
                HttpStatus.CREATED.value(),
                "Nurse registered successfully",
                responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/nurses
    @GetMapping
    public ResponseEntity<ResponseStructure<List<NurseResponseDTO>>> getAllNurses() {

        List<NurseResponseDTO> nurses = nurseService.getAllNurses();

        ResponseStructure<List<NurseResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Nurses fetched successfully",
                nurses);

        return ResponseEntity.ok(response);
    }

    // GET /api/nurses/{employeeId}
    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseStructure<NurseResponseDTO>> getNurseById(
            @PathVariable Integer employeeId) {

        NurseResponseDTO responseDTO = nurseService.getNurseById(employeeId);

        ResponseStructure<NurseResponseDTO> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Nurse fetched successfully",
                responseDTO);

        return ResponseEntity.ok(response);
    }

    // PUT /api/nurses/{employeeId}
    @PutMapping("/{employeeId}")
    public ResponseEntity<ResponseStructure<NurseResponseDTO>> updateNurse(
            @PathVariable Integer employeeId,
            @Valid @RequestBody NurseRequestDTO request) {

        NurseResponseDTO responseDTO = nurseService.updateNurse(employeeId, request);

        ResponseStructure<NurseResponseDTO> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Nurse updated successfully",
                responseDTO);

        return ResponseEntity.ok(response);
    }
}
