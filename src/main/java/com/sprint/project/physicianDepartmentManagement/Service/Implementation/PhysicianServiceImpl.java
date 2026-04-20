package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.PhysicianRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.PhysicianResponseDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.ResponseStructure;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.PhysicianService;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
@Service
public class PhysicianServiceImpl implements PhysicianService {
	
	 @Autowired
	 private  PhysicianRepository physicianRepository;

	    public PhysicianServiceImpl(PhysicianRepository physicianRepository) {
	        this.physicianRepository = physicianRepository;
	    }

	    // ---------------- CREATE ----------------
	    @Override
	    public ResponseStructure<PhysicianResponseDto> createPhysician(PhysicianRequestDto dto) {

	        if (physicianRepository.existsById(dto.getEmployeeId())) {
	            throw new DuplicateResourceException("Physician is already existed"+ dto.getEmployeeId());
	        }

	        boolean ssnExists = physicianRepository.findAll().stream()
	                .anyMatch(p -> p.getSsn().equals(dto.getSsn()));

	        if (ssnExists) {
	            throw new DuplicateResourceException("Physician is alrady existed"+dto.getSsn());
	        }

	        PhysicianEntity entity = mapToEntity(dto);
	        PhysicianEntity saved = physicianRepository.save(entity);

	        PhysicianResponseDto responseDto = mapToResponse(saved);

	        return new ResponseStructure<>(201, "Physician created successfully", responseDto);
	    }

	    // ---------------- GET ALL ----------------
	    @Override
	    public ResponseStructure<List<PhysicianResponseDto>> getAllPhysicians() {

	        List<PhysicianResponseDto> list = physicianRepository.findAll()
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());

	        return new ResponseStructure<>(200, "All physicians fetched", list);
	    }

	    // ---------------- GET BY ID ----------------
	    @Override
	    public ResponseStructure<PhysicianResponseDto> getPhysicianById(Integer employeeId) {

	        PhysicianEntity entity = physicianRepository.findById(employeeId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Physician is not found"+employeeId));

	        return new ResponseStructure<>(200, "Physician found", mapToResponse(entity));
	    }

	    // ---------------- UPDATE ----------------
	    @Override
	    public ResponseStructure<PhysicianResponseDto> updatePhysician(Integer employeeId, PhysicianRequestDto dto) {

	        PhysicianEntity existing = physicianRepository.findById(employeeId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Physician is not found"+employeeId));

	        boolean ssnConflict = physicianRepository.findAll().stream()
	                .anyMatch(p -> p.getSsn().equals(dto.getSsn())
	                        && !p.getEmployeeId().equals(employeeId));

	        if (ssnConflict) {
	            throw new DuplicateResourceException("Physician is already existed"+dto.getSsn());
	        }

	        existing.setName(dto.getName());
	        existing.setPosition(dto.getPosition());
	        existing.setSsn(dto.getSsn());

	        PhysicianEntity updated = physicianRepository.save(existing);

	        return new ResponseStructure<>(200, "Physician updated successfully", mapToResponse(updated));
	    }

	    // ---------------- DELETE ----------------
	    @Override
	    public ResponseStructure<String> deletePhysician(Integer employeeId) {

	        PhysicianEntity physician = physicianRepository.findById(employeeId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Physician is not found"+employeeId));

	        if (physician.getDepartments() != null && !physician.getDepartments().isEmpty()) {
	            throw new InvalidDnDOperationException(
	                    "Cannot delete Physician with EmployeeID " + employeeId +
	                            " because they are head of department.");
	        }

	        physicianRepository.delete(physician);

	        return new ResponseStructure<>(200, "Physician deleted successfully", "Deleted");
	    }

	    // ---------------- MAPPERS ----------------
	    private PhysicianEntity mapToEntity(PhysicianRequestDto dto) {
	        PhysicianEntity entity = new PhysicianEntity();
	        entity.setEmployeeId(dto.getEmployeeId());
	        entity.setName(dto.getName());
	        entity.setPosition(dto.getPosition());
	        entity.setSsn(dto.getSsn());
	        return entity;
	    }

	    private PhysicianResponseDto mapToResponse(PhysicianEntity entity) {
	        return new PhysicianResponseDto(
	                entity.getEmployeeId(),
	                entity.getName(),
	                entity.getPosition(),
	                entity.getSsn()
	        );
	    }

}
