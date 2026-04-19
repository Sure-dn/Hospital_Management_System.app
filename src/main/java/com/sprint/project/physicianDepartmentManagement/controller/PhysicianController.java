package com.sprint.project.physicianDepartmentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.physicianDepartmentManagement.Dto.requestdto.PhysicianRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.responseDto.ResponseStructure;
import com.sprint.project.physicianDepartmentManagement.Service.implementation.PhysicianServiceImpl;


import jakarta.validation.Valid;

	@RestController
	@RequestMapping("/api/physicians")
	@Validated
	public class PhysicianController {

	    @Autowired
	    private PhysicianServiceImpl physicianService;

	    //  CREATE 
	    @PostMapping
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> createPhysician(
	            @Valid @RequestBody PhysicianRequestDto dto) {

	        return new ResponseEntity<>(
	                physicianService.createPhysician(dto),
	                HttpStatus.CREATED
	        );
	    }
                          
	    // GET ALL
	    @GetMapping
	    public ResponseEntity<ResponseStructure<List<PhysicianResponseDto>>> getAllPhysicians() {

	        return ResponseEntity.ok(
	                physicianService.getAllPhysicians()
	        );
	    }

	    // ---------------- GET BY ID ----------------
	    @GetMapping("/{id}")
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> getPhysicianById(
	            @PathVariable Integer id) {

	        return ResponseEntity.ok(
	                physicianService.getPhysicianById(id)
	        );
	    }

	    //  UPDATE 
	    @PutMapping("/{id}")
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> updatePhysician(
	            @PathVariable Integer id,
	            @Valid @RequestBody PhysicianRequestDto dto) {

	        return ResponseEntity.ok(
	                physicianService.updatePhysician(id, dto)
	        );
	    }

	    // ---------------- DELETE ----------------
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseStructure<String>> deletePhysician(
	            @PathVariable Integer id) {

	        return ResponseEntity.ok(
	                physicianService.deletePhysician(id)
	        );
	    }
	}
    

