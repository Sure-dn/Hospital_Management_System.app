package com.sprint.project.physicianDepartmentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.DepartmentRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.DepartmentResponseDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.service.DepartmentService;

import java.util.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/departments")
public class DepartmentController {
	@Autowired
	private  DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //  CREATE
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentRequestDto requestDto) {

        DepartmentResponseDto response = departmentService.createDepartment(requestDto);
        return ResponseEntity.ok(response);
    }

    //  GET ALL
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    //  GET BY ID
    @GetMapping("/{departmentid}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Integer departmentid) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentid));
    }

    //  UPDATE
    @PutMapping("/{departmentid}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable Integer departmentid,
            @Valid @RequestBody DepartmentRequestDto requestDto) {

        return ResponseEntity.ok(departmentService.updateDepartment(departmentid, requestDto));
    }

    //  GET HEAD OF DEPARTMENT
    @GetMapping("/{departmentid}/head")
    public ResponseEntity<PhysicianResponseDto> getDepartmentHead(@PathVariable Integer departmentid) {
        return ResponseEntity.ok(departmentService.getDepartmentHead(departmentid));
    }

}
