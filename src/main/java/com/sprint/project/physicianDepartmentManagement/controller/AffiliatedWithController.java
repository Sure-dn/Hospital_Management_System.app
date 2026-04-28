package com.sprint.project.physicianDepartmentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.AffiliatedWithRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.AffiliatedWithResponseDto;
import com.sprint.project.physicianDepartmentManagement.service.AffiliatedWithService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/affiliations")
public class AffiliatedWithController {
	@Autowired
	 private  AffiliatedWithService affiliatedWithService;

	    public AffiliatedWithController(AffiliatedWithService affiliatedWithService) {
	        this.affiliatedWithService = affiliatedWithService;
	    }

	    //  CREATE AFFILIATION 
	    @PostMapping("/physician/{physicianId}")
	    public ResponseEntity<AffiliatedWithResponseDto> createAffiliation(
	          @PathVariable Integer physicianId,
	          @Valid  @RequestBody AffiliatedWithRequestDto requestDto) {

	        AffiliatedWithResponseDto response =
	                affiliatedWithService.createAffiliation(physicianId, requestDto);

	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }

	    // GET BY PHYSICIAN 
	    @GetMapping("/physician/{physicianId}")
	    public ResponseEntity<List<AffiliatedWithResponseDto>> getByPhysician(
	            @PathVariable Integer physicianId) {

	        List<AffiliatedWithResponseDto> response =
	                affiliatedWithService.getAffiliationsByPhysician(physicianId);

	        return ResponseEntity.ok(response);
	    }

	    //  GET BY DEPARTMENT 
	    @GetMapping("/department/{departmentId}")
	    public ResponseEntity<List<AffiliatedWithResponseDto>> getByDepartment(
	            @PathVariable Integer departmentId) {

	        List<AffiliatedWithResponseDto> response =
	                affiliatedWithService.getPhysiciansByDepartment(departmentId);

	        return ResponseEntity.ok(response);
	    }

}
