package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.patientAppointment.DTO.RequestDTO.PatientRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.PatientResponseDTO;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.patientAppointment.Service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repo;

    
    private PatientEntity toEntity(PatientRequestDTO dto) {
        PatientEntity entity = new PatientEntity();
        entity.setSsn(dto.getSsn());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setInsuranceId(dto.getInsuranceId());
        entity.setPcp(dto.getPcp());
        return entity;
    }

    private PatientResponseDTO toResponseDTO(PatientEntity entity) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setSsn(entity.getSsn());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setInsuranceId(entity.getInsuranceId());
        dto.setPcp(entity.getPcp());
        return dto;
    }

    //  CREATE 
    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {

        if (requestDTO.getSsn() == null || requestDTO.getSsn() <= 0) {
            throw new BadRequestException("Invalid SSN");
        }

        if (repo.existsById(requestDTO.getSsn())) {
            throw new DuplicateResourceException(
                    "Patient already exists with SSN: " + requestDTO.getSsn());
        }

        PatientEntity saved = repo.save(toEntity(requestDTO));
        return toResponseDTO(saved);
    }

    // GET BY ID 
    @Override
    public PatientResponseDTO getPatientById(Integer ssn) {

        PatientEntity entity = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        return toResponseDTO(entity);
    }

    // GET ALL 
    @Override
    public List<PatientResponseDTO> getAllPatients() {

        return repo.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    //  UPDATE 
    @Override
    public PatientResponseDTO updatePatient(Integer ssn, PatientRequestDTO requestDTO) {

        PatientEntity existing = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        if (!ssn.equals(requestDTO.getSsn())) {
            throw new BadRequestException("SSN mismatch between path and body");
        }

        existing.setName(requestDTO.getName());
        existing.setAddress(requestDTO.getAddress());
        existing.setPhone(requestDTO.getPhone());
        existing.setInsuranceId(requestDTO.getInsuranceId());
        existing.setPcp(requestDTO.getPcp());

        return toResponseDTO(repo.save(existing));
    }

    //  DELETE 
    @Override
    public void deletePatient(Integer ssn) {

        PatientEntity existing = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        repo.delete(existing);
    }

    //  GET PCP 
    @Override
    public Integer getPCP(Integer ssn) {

        return repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn))
                .getPcp();
    }

    //  UPDATE PCP 
    @Override
    public PatientResponseDTO updatePCP(Integer ssn, Integer physicianId) {

        if (physicianId == null || physicianId <= 0) {
            throw new BadRequestException("Invalid Physician ID");
        }

        PatientEntity patient = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        patient.setPcp(physicianId);

        return toResponseDTO(repo.save(patient));
    }
}


