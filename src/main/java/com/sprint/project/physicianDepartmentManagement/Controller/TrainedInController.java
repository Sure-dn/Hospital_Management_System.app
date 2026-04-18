
	package com.sprint.project.physicianDepartmentManagement.Controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.TrainedInRequestDto;
	import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.TrainedInResponseDto;
	import com.sprint.project.physicianDepartmentManagement.Service.TrainedInService;

	@RestController
	@RequestMapping("/api/physicians")
	public class TrainedInController {

	    @Autowired
	    private TrainedInService trainedInService;

	    // ================= CREATE TRAINING =================
	    @PostMapping("/{employeeId}/trainings")
	    public ResponseEntity<TrainedInResponseDto> createTraining(
	            @PathVariable Integer employeeId,
	            @RequestBody TrainedInRequestDto requestDTO) {

	        // inject physicianId into DTO (important fix)
	        requestDTO.setPhysicianId(employeeId);

	        return ResponseEntity.ok(trainedInService.createTraining(requestDTO));
	    }

	    // ================= GET ALL TRAININGS =================
	    @GetMapping("/{employeeId}/trainings")
	    public ResponseEntity<List<TrainedInResponseDto>> getAllTrainings(
	            @PathVariable Integer employeeId) {

	        return ResponseEntity.ok(
	                trainedInService.getTrainingsByPhysician(employeeId)
	        );
	    }

	    // ================= GET VALID TRAININGS =================
	    @GetMapping("/{employeeId}/trainings/valid")
	    public ResponseEntity<List<TrainedInResponseDto>> getValidTrainings(
	            @PathVariable Integer employeeId) {

	        return ResponseEntity.ok(
	                trainedInService.getValidTrainingsByPhysician(employeeId)
	        );
	    }
	}


