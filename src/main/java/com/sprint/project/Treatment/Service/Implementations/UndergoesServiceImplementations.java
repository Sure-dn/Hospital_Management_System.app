package com.sprint.project.Treatment.Service.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.DTO.UndergoesRequestDTO;
import com.sprint.project.Treatment.Entity.*;
import com.sprint.project.Treatment.Repository.UndergoesRepository;
import com.sprint.project.Treatment.Service.UndergoesService;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.Treatment.Repository.StayRepository;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;

@Service
public class UndergoesServiceImplementations implements UndergoesService {

    @Autowired
    private UndergoesRepository undergoesRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ProceduresRepository proceduresRepository;

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private NurseRepository nurseRepository;

    // 🔁 ENTITY → DTO
    private UndergoesRequestDTO mapToDTO(UndergoesEntity entity) {
        UndergoesRequestDTO dto = new UndergoesRequestDTO();

        dto.setPatientId(entity.getPatient().getSsn());
        dto.setProcedureId(entity.getProcedures().getCode());
        dto.setStayId(entity.getStay().getStayId());
        dto.setDateUndergoes(entity.getUndergoesId().getDateUndergoes());

        if (entity.getPhysician() != null)
            dto.setPhysicianId(entity.getPhysician().getEmployeeId());

        if (entity.getAssistingNurse() != null)
            dto.setNurseId(entity.getAssistingNurse().getEmployeeId());

        return dto;
    }

    @Override
    public UndergoesRequestDTO assignTreatment(UndergoesRequestDTO dto) {

        PatientEntity patient = patientRepository.findById(dto.getPatientId().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        ProceduresEntity procedure = proceduresRepository.findById(dto.getProcedureId())
                .orElseThrow(() -> new ResourceNotFoundException("Procedure not found"));

        StayEntity stay = stayRepository.findById(dto.getStayId())
                .orElseThrow(() -> new ResourceNotFoundException("Stay not found"));

        if (!stay.getPatient().getSsn().equals(patient.getSsn())) {
            throw new BadRequestException("Patient mismatch with stay");
        }

        PhysicianEntity physician = null;
        if (dto.getPhysicianId() != null) {
            physician = physicianRepository.findById(dto.getPhysicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("Physician not found"));
        }

        NurseEntity nurse = null;
        if (dto.getNurseId() != null) {
            nurse = nurseRepository.findById(dto.getNurseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nurse not found"));
        }

        UndergoesId id = new UndergoesId(
                dto.getPatientId(),
                dto.getProcedureId(),
                dto.getStayId(),
                dto.getDateUndergoes()
        );

        if (undergoesRepository.existsById(id)) {
            throw new BadRequestException("Treatment already exists");
        }

        UndergoesEntity entity = new UndergoesEntity(
                id, patient, procedure, stay, physician, nurse
        );

        UndergoesEntity saved = undergoesRepository.save(entity);

        return mapToDTO(saved); // 🔁 convert before returning
    }

    @Override
    public List<UndergoesRequestDTO> getAllTreatments() {
        return undergoesRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UndergoesRequestDTO> getTreatmentByPatient(Integer patientId) {
        return undergoesRepository.findAll()
                .stream()
                .filter(u -> u.getPatient().getSsn().equals(patientId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UndergoesRequestDTO> getTreatmentByStay(Integer stayId) {
        return undergoesRepository.findAll()
                .stream()
                .filter(u -> u.getStay().getStayId().equals(stayId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UndergoesRequestDTO deleteTreatment(UndergoesId id) {

        UndergoesEntity existing = undergoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found"));

        undergoesRepository.delete(existing);

        return mapToDTO(existing);
    }
}