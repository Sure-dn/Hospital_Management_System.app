package com.sprint.project.physicianDepartmentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.PhysicianRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.ResponseStructure;
import com.sprint.project.physicianDepartmentManagement.Service.PhysicianService;


import jakarta.validation.Valid;

	@RestController
	@RequestMapping("/api/physicians")
	@Validated
	public class PhysicianController {

	    @Autowired
	    private PhysicianService physicianService;

	    // ---------------- CREATE ----------------
	    @PostMapping
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> createPhysician(
	            @Valid @RequestBody PhysicianRequestDto dto) {

	        return new ResponseEntity<>(
	                physicianService.createPhysician(dto),
	                HttpStatus.CREATED
	        );
	    }

	    // ---------------- GET ALL ----------------
	    @GetMapping
	    public ResponseEntity<ResponseStructure<List<PhysicianResponseDto>>> getAllPhysicians() {

	        return ResponseEntity.ok(
	                physicianService.getAllPhysicians()
	        );
	    }

	    // ---------------- GET BY ID ----------------
	    @GetMapping("/{employeeid}")
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> getPhysicianById(
	            @PathVariable Integer employeeid) {

	        return ResponseEntity.ok(
	                physicianService.getPhysicianById(employeeid)
	        );
	    }

	    // ---------------- UPDATE ----------------
	    @PutMapping("/{employeeid}")
	    public ResponseEntity<ResponseStructure<PhysicianResponseDto>> updatePhysician(
	            @PathVariable Integer id,
	            @Valid @RequestBody PhysicianRequestDto dto) {

	        return ResponseEntity.ok(
	                physicianService.updatePhysician(id, dto)
	        );
	    }

	    // ---------------- DELETE ----------------
	    @DeleteMapping("/{employeeid}")
	    public ResponseEntity<ResponseStructure<String>> deletePhysician(
	            @PathVariable Integer employeeid) {

	        return ResponseEntity.ok(
	                physicianService.deletePhysician(employeeid)
	        );
	    }
	}
    

