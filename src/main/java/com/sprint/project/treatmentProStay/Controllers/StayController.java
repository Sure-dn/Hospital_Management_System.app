package com.sprint.project.treatmentprostay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.treatmentprostay.DTO.ResponseStructure;
import com.sprint.project.treatmentprostay.DTO.StayRequestDTO;
import com.sprint.project.treatmentprostay.DTO.StayResponseDTO;
import com.sprint.project.treatmentprostay.entity.StayEntity;
import com.sprint.project.treatmentprostay.service.StayService;

@RestController
@RequestMapping("/api/stays")
public class StayController {

    @Autowired
    private StayService stayService;

    @PostMapping
    public ResponseEntity<ResponseStructure<StayResponseDTO>> admit(
            @RequestBody StayRequestDTO dto) {

        StayEntity saved = stayService.admitPatient(dto);

        StayResponseDTO response = new StayResponseDTO(
                saved.getStayId(),
                saved.getPatient().getSsn(),
                saved.getRoom().getRoomNumber(),
                saved.getStayStart(),
                saved.getStayEnd()
        );

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Patient admitted", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<StayResponseDTO>> get(@PathVariable Integer id) {

        StayEntity stay = stayService.getStayById(id);

        StayResponseDTO response = new StayResponseDTO(
                stay.getStayId(),
                stay.getPatient().getSsn(),
                stay.getRoom().getRoomNumber(),
                stay.getStayStart(),
                stay.getStayEnd()
        );

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Success", response));
    }

    @PostMapping("/discharge/{id}")
    public ResponseEntity<ResponseStructure<StayResponseDTO>> discharge(@PathVariable Integer id) {

        StayEntity stay = stayService.dischargePatient(id);

        StayResponseDTO response = new StayResponseDTO(
                stay.getStayId(),
                stay.getPatient().getSsn(),
                stay.getRoom().getRoomNumber(),
                stay.getStayStart(),
                stay.getStayEnd()
        );

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Discharged", response));
    }
}