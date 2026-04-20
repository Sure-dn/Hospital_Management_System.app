package com.sprint.project.nurseoncallroom.controller;

import com.sprint.project.nurseoncallroom.dto.ResponseStructure;
import com.sprint.project.nurseoncallroom.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.OnCallResponseDTO;
import com.sprint.project.nurseoncallroom.service.OnCallService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OnCallController {

    private final OnCallService onCallService;

    public OnCallController(OnCallService onCallService) {
        this.onCallService = onCallService;
    }

    // POST /api/nurses/{employeeId}/on-call
    @PostMapping("/nurses/{employeeId}/on-call")
    public ResponseEntity<ResponseStructure<OnCallResponseDTO>> assignOnCall(
            @PathVariable Integer employeeId,
            @Valid @RequestBody OnCallRequestDTO request) {

        OnCallResponseDTO responseDTO = onCallService.assignOnCall(employeeId, request);

        ResponseStructure<OnCallResponseDTO> response = ResponseStructure.success(
                HttpStatus.CREATED.value(),
                "On-call shift assigned successfully",
                responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/nurses/{employeeId}/on-call
    @GetMapping("/nurses/{employeeId}/on-call")
    public ResponseEntity<ResponseStructure<List<OnCallResponseDTO>>> getOnCallByNurse(
            @PathVariable Integer employeeId) {

        List<OnCallResponseDTO> list = onCallService.getOnCallByNurse(employeeId);

        ResponseStructure<List<OnCallResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "On-call shifts fetched for nurse",
                list);

        return ResponseEntity.ok(response);
    }

    // GET /api/on-call?floor={floor}&code={code}
    @GetMapping("/on-call")
    public ResponseEntity<ResponseStructure<List<OnCallResponseDTO>>> getOnCallByBlock(
            @RequestParam Integer floor,
            @RequestParam Integer code) {

        List<OnCallResponseDTO> list = onCallService.getOnCallByBlock(floor, code);

        ResponseStructure<List<OnCallResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "On-call shifts fetched for block",
                list);

        return ResponseEntity.ok(response);
    }

    // DELETE /api/nurses/{employeeId}/on-call
    @DeleteMapping("/nurses/{employeeId}/on-call")
    public ResponseEntity<ResponseStructure<String>> deleteOnCallByNurse(
            @PathVariable Integer employeeId) {

        onCallService.deleteOnCall(employeeId);

        ResponseStructure<String> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "On-call shifts deleted for nurse with ID: " + employeeId,
                null);

        return ResponseEntity.ok(response);
    }
}
