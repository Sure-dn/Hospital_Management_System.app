package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.Repository.NurseRepository;
import com.sprint.project.patientAppointment.DTO.RequestDTO.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.AppointmentResponseDTO;
import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.exception.InvalidRequestException;
import com.sprint.project.patientAppointment.Repository.*;
import com.sprint.project.patientAppointment.Service.AppointmentService;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PhysicianRepository physicianRepo;

    @Autowired
    private NurseRepository nurseRepo;

    private AppointmentEntity toEntity(AppointmentRequestDTO dto) {

        AppointmentEntity entity = new AppointmentEntity();
        

        entity.setAppointmentId(dto.getAppointmentId());

        // Patient mapping
        PatientEntity patient = patientRepo.findById(dto.getPatientSsn())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + dto.getPatientSsn()));
        entity.setPatient(patient);

        // Physician mapping
        PhysicianEntity physician = physicianRepo.findById(dto.getPhysicianId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Physician not found with ID: " + dto.getPhysicianId()));
        entity.setPhysician(physician);

        // Nurse mapping
        if (dto.getPrepNurseId() != null) {
            NurseEntity nurse = nurseRepo.findById(dto.getPrepNurseId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Nurse not found with ID: " + dto.getPrepNurseId()));
            entity.setPrepNurse(nurse);
        }
        

        entity.setStart(dto.getStarttime());
        entity.setEnd(dto.getEndtime());
        entity.setExaminationRoom(dto.getExaminationRoom());

        return entity;
    }

    // ================= ENTITY → DTO =================
    private AppointmentResponseDTO toDTO(AppointmentEntity entity) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setAppointmentId(entity.getAppointmentId());

        dto.setPatientSsn(entity.getPatient().getSsn());
        dto.setPatientName(entity.getPatient().getName());

        dto.setPhysicianId(entity.getPhysician().getEmployeeId());
        dto.setPhysicianName(entity.getPhysician().getName());

        if (entity.getPrepNurse() != null) {
            dto.setPrepNurseId(entity.getPrepNurse().getEmployeeId());
            dto.setPrepNurseName(entity.getPrepNurse().getName());
        }

        dto.setStarttime(entity.getStart());
        dto.setEndtime(entity.getEnd());
        dto.setExaminationRoom(entity.getExaminationRoom());

        return dto;
    }

    //  CREATE 
    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {

        if (dto.getAppointmentId() == null) {
            throw new BadRequestException("Appointment ID cannot be null");
        }

        if (repo.existsById(dto.getAppointmentId())) {
            throw new DuplicateResourceException(
                    "Appointment already exists with ID: " + dto.getAppointmentId());
        }

        validateTime(dto.getStarttime(), dto.getEndtime());

        AppointmentEntity saved = repo.save(toEntity(dto));
        return toDTO(saved);
    }

    //  GET ONE 
    @Override
    public AppointmentResponseDTO getAppointmentById(Integer id) {

        AppointmentEntity entity = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));

        return toDTO(entity);
    }

    // GET ALL 
    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {

        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //  UPDATE 
    @Override
    public AppointmentResponseDTO updateAppointment(Integer id, AppointmentRequestDTO dto) {

        AppointmentEntity existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));

        validateTime(dto.getStarttime(), dto.getEndtime());

        existing.setStart(dto.getStarttime());
        existing.setEnd(dto.getEndtime());
        existing.setExaminationRoom(dto.getExaminationRoom());

        return toDTO(repo.save(existing));
    }

    // DELETE 
    @Override
    public void deleteAppointment(Integer id) {

        AppointmentEntity existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));

        repo.delete(existing);
    }

    // GET BY PATIENT 
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Integer ssn) {

        if (!patientRepo.existsById(ssn)) {
            throw new ResourceNotFoundException("Patient not found with SSN: " + ssn);
        }

        return repo.findByPatient_Ssn(ssn)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //GET BY PHYSICIAN
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPhysician(Integer physicianId) {

        if (physicianId == null || physicianId <= 0) {
            throw new BadRequestException("Invalid Physician ID");
        }

        return repo.findByPhysician_EmployeeId(physicianId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //  GET BY DATE 
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date) {

        if (date == null) {
            throw new BadRequestException("Date cannot be null");
        }

        return repo.findByDate(date)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //  VALIDATION 
    private void validateTime(LocalDateTime start, LocalDateTime end) {

        if (start == null || end == null) {
            throw new BadRequestException("Start and End time cannot be null");
        }

        if (!start.isBefore(end)) {
            throw new InvalidRequestException("Start time must be before End time");
        }

        if (start.isBefore(LocalDateTime.now())) {
            throw new InvalidRequestException("Cannot book appointment in the past");
        }
    }
}
