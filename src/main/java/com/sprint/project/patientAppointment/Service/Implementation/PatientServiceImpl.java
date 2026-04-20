package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.patientAppointment.DTO.RequestDTO.PatientRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.PatientResponseDTO;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.exception.PatientAlreadyExistsException;
import com.sprint.project.patientAppointment.exception.PatientNotFoundException;
import com.sprint.project.patientAppointment.repository.PatientRepository;
import com.sprint.project.patientAppointment.Service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repo;

    //  MAPPER 

    private PatientEntity mapToEntity(PatientRequestDTO dto) {
        PatientEntity entity = new PatientEntity();
        entity.setSsn(dto.getSsn());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setInsuranceId(dto.getInsuranceId());
        entity.setPcp(dto.getPcp());
        return entity;
    }

    private PatientResponseDTO mapToResponse(PatientEntity entity) {
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
    public PatientResponseDTO createPatient(PatientRequestDTO dto) {

        if (dto == null || dto.getSsn() == null || dto.getSsn() <= 0) {
            throw new PatientAlreadyExistsException("Invalid Patient Data");
        }

        if (repo.existsById(dto.getSsn())) {
            throw new PatientAlreadyExistsException(
                    "Patient already exists with SSN: " + dto.getSsn());
        }

        PatientEntity saved = repo.save(mapToEntity(dto));
        return mapToResponse(saved);
    }

    //  GET BY ID 
    @Override
    public PatientResponseDTO getPatientById(Integer ssn) {

        PatientEntity entity = repo.findById(ssn)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with SSN: " + ssn));

        return mapToResponse(entity);
    }

    //GET ALL 
    @Override
    public List<PatientResponseDTO> getAllPatients() {

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE 
    @Override
    public PatientResponseDTO updatePatient(Integer ssn, PatientRequestDTO dto) {

        PatientEntity existing = repo.findById(ssn)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with SSN: " + ssn));

        // Optional strict validation
        if (!ssn.equals(dto.getSsn())) {
            throw new PatientNotFoundException("SSN mismatch between path and body");
        }

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setPhone(dto.getPhone());
        existing.setInsuranceId(dto.getInsuranceId());
        existing.setPcp(dto.getPcp());

        return mapToResponse(repo.save(existing));
    }

    //  DELETE 
    @Override
    public void deletePatient(Integer ssn) {

        PatientEntity entity = repo.findById(ssn)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with SSN: " + ssn));

        repo.delete(entity);
    }

    // GET PCP 
    @Override
    public Integer getPCP(Integer ssn) {

        PatientEntity entity = repo.findById(ssn)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with SSN: " + ssn));

        return entity.getPcp();
    }

    //  UPDATE PCP 
    @Override
    public PatientResponseDTO updatePCP(Integer ssn, Integer physicianId) {

        if (physicianId == null || physicianId <= 0) {
            throw new PatientNotFoundException("Invalid Physician ID");
        }

        PatientEntity entity = repo.findById(ssn)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with SSN: " + ssn));

        entity.setPcp(physicianId);

        return mapToResponse(repo.save(entity));
    }
}