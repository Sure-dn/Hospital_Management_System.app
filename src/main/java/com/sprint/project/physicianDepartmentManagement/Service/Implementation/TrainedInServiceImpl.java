package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.TrainedInRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.TrainedInResponseDto;
import com.sprint.project.physicianDepartmentManagement.Entity.*;
import com.sprint.project.physicianDepartmentManagement.Repository.*;
import com.sprint.project.physicianDepartmentManagement.Service.TrainedInService;
import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.exception.ValidationException;

	@Service
	public class TrainedInServiceImpl implements TrainedInService {
        @Autowired
	    private  TrainedInRepository trainedInRepository;
        
        @Autowired
	    private  PhysicianRepository physicianRepository;
        
        @Autowired
	    private final ProceduresRepository proceduresRepository;

	    public TrainedInServiceImpl(TrainedInRepository trainedInRepository,
	                                PhysicianRepository physicianRepository,
	                                ProceduresRepository proceduresRepository) {
	        this.trainedInRepository = trainedInRepository;
	        this.physicianRepository = physicianRepository;
	        this.proceduresRepository = proceduresRepository;
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
	                .orElseThrow(() -> new ResourceNotFoundException("Training not found"));

	        return mapToResponse(entity);
	    }

	    @Override
	    public TrainedInResponseDto updateTraining(TrainedInId id, TrainedInRequestDto requestDTO) {

	        TrainedInEntity entity = trainedInRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Training not found"));

	        if (requestDTO.getCertificationExpiry().isBefore(LocalDate.now())) {
	            throw new ValidationException("Expiry must be future date");
	        }

	        entity.setCertificationExpiry(requestDTO.getCertificationExpiry());

	        return mapToResponse(trainedInRepository.save(entity));
	    }

	    @Override
	    public void deleteTraining(TrainedInId id) {

	        if (!trainedInRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Training not found");
	        }

	        trainedInRepository.deleteById(id);
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



		@Override
		public TrainedInResponseDto createTraining(TrainedInRequestDto requestDTO) {
			// TODO Auto-generated method stub
			 Integer physicianId = requestDTO.getPhysicianId();
		        Integer treatmentCode = requestDTO.getTreatmentCode();

		        PhysicianEntity physician = physicianRepository.findById(physicianId)
		                .orElseThrow(() -> new ResourceNotFoundException("Physician not found: " + physicianId));

		        ProceduresEntity procedure = proceduresRepository.findById(treatmentCode)
		                .orElseThrow(() -> new ResourceNotFoundException("Procedure not found: " + treatmentCode));

		        TrainedInId id = new TrainedInId(physicianId, treatmentCode);

		        if (trainedInRepository.existsById(id)) {
		            throw new DuplicateResourceException("Training already exists");
		        }

		        if (requestDTO.getCertificationExpiry().isBefore(LocalDate.now())) {
		            throw new ValidationException("Expiry must be future date");
		        }

		        TrainedInEntity entity = new TrainedInEntity();
		        entity.setTrainedInId(id);
		        entity.setPhysician(physician);
		        entity.setTreatment(procedure);
		        entity.setCertificationExpiry(requestDTO.getCertificationExpiry());

		        return mapToResponse(trainedInRepository.save(entity));
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
	}
