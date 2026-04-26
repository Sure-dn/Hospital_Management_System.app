package com.sprint.project.physicianDepartmentManagement.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.TrainedInRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.TrainedInResponseDto;
import com.sprint.project.physicianDepartmentManagement.entity.*;
import com.sprint.project.physicianDepartmentManagement.exception.DuplicateTrainingException;
import com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException;
import com.sprint.project.physicianDepartmentManagement.exception.TrainingNotFoundException;
import com.sprint.project.physicianDepartmentManagement.repository.*;
import com.sprint.project.physicianDepartmentManagement.service.TrainedInService;
import com.sprint.project.treatmentprostayy.entities.ProceduresEntity;
import com.sprint.project.treatmentprostayy.repositories.ProceduresRepository;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.exception.ValidationException;

	@Service
	public class TrainedInServiceImpl implements TrainedInService {
	

		    @Autowired
		    private TrainedInRepository trainedInRepository;

		    @Autowired
		    private PhysicianRepository physicianRepository;

		    @Autowired
		    private ProceduresRepository proceduresRepository;

		    public TrainedInServiceImpl(TrainedInRepository trainedInRepository,
		                                PhysicianRepository physicianRepository,
		                                ProceduresRepository proceduresRepository) {
		        this.trainedInRepository = trainedInRepository;
		        this.physicianRepository = physicianRepository;
		        this.proceduresRepository = proceduresRepository;
		    }

		    @Override
		    public TrainedInResponseDto createTraining(TrainedInRequestDto requestDTO) {

		        PhysicianEntity physician = physicianRepository.findById(requestDTO.getPhysicianId())
		                .orElseThrow(() ->
		                        new PhysicianNotFoundException("Physician not found: " + requestDTO.getPhysicianId()));

		        ProceduresEntity procedure = proceduresRepository.findById(requestDTO.getTreatmentCode())
		                .orElseThrow(() ->
		                        new ResourceNotFoundException("Procedure not found: " + requestDTO.getTreatmentCode()));

		        TrainedInId id = new TrainedInId(requestDTO.getPhysicianId(), requestDTO.getTreatmentCode());

		        if (trainedInRepository.existsById(id)) {
		            throw new DuplicateTrainingException("Training already exists");
		        }

		        validateExpiry(requestDTO.getCertificationExpiry());

		        TrainedInEntity entity = mapToEntity(id, physician, procedure, requestDTO);

		        return mapToResponse(trainedInRepository.save(entity));
		    }

		    @Override
		    public List<TrainedInResponseDto> getAllTrainings() {
		        return trainedInRepository.findAll()
		                .stream()
		                .map(this::mapToResponse)
		                .collect(Collectors.toList());
		    }

		    @Override
		    public TrainedInResponseDto getTrainingById(TrainedInId id) {

		        TrainedInEntity entity = trainedInRepository.findById(id)
		                .orElseThrow(() ->
		                        new TrainingNotFoundException("Training not found"));

		        return mapToResponse(entity);
		    }

		    @Override
		    public TrainedInResponseDto updateTraining(TrainedInId id, TrainedInRequestDto requestDTO) {

		        TrainedInEntity entity = trainedInRepository.findById(id)
		                .orElseThrow(() ->
		                        new TrainingNotFoundException("Training not found"));

		        validateExpiry(requestDTO.getCertificationExpiry());

		        entity.setCertificationExpiry(requestDTO.getCertificationExpiry());

		        return mapToResponse(trainedInRepository.save(entity));
		    }

		    @Override
		    public void deleteTraining(TrainedInId id) {

		        if (!trainedInRepository.existsById(id)) {
		            throw new TrainingNotFoundException("Training not found");
		        }

		        trainedInRepository.deleteById(id);
		    }

		    @Override
		    public List<TrainedInResponseDto> getTrainingsByPhysician(Integer employeeId) {

		        return trainedInRepository.findByPhysician_EmployeeId(employeeId)
		                .stream()
		                .map(this::mapToResponse)
		                .collect(Collectors.toList());
		    }

		    @Override
		    public List<TrainedInResponseDto> getValidTrainingsByPhysician(Integer employeeId) {

		        return trainedInRepository.findValidTrainingsByPhysicianId(employeeId, LocalDate.now())
		                .stream()
		                .map(this::mapToResponse)
		                .collect(Collectors.toList());
		    }

		    // MAPPERS

		    private TrainedInEntity mapToEntity(TrainedInId id,
		                                        PhysicianEntity physician,
		                                        ProceduresEntity procedure,
		                                        TrainedInRequestDto dto) {

		        TrainedInEntity entity = new TrainedInEntity();
		        entity.setTrainedInId(id);
		        entity.setPhysician(physician);
		        entity.setTreatment(procedure);
		        entity.setCertificationExpiry(dto.getCertificationExpiry());
		        return entity;
		    }

		    private TrainedInResponseDto mapToResponse(TrainedInEntity entity) {

		        return new TrainedInResponseDto(
		                entity.getPhysician().getEmployeeId(),
		                entity.getPhysician().getName(),
		                entity.getTreatment().getCode(),
		                entity.getTreatment().getName(),
		                entity.getCertificationExpiry(),
		                entity.getCertificationExpiry().isAfter(LocalDate.now())
		        );
		    }

		    // VALIDATION 

		    private void validateExpiry(LocalDate expiry) {
		        if (expiry == null) {
		            throw new ValidationException("Expiry date cannot be null");
		        }
		        if (expiry.isBefore(LocalDate.now())) {
		            throw new ValidationException("Expiry must be a future date");
		        }
		    }
		}
	
