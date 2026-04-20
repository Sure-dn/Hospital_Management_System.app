package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.patientAppointment.DTO.RequestDTO.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.AppointmentResponseDTO;
import com.sprint.project.patientAppointment.entity.*;
import com.sprint.project.patientAppointment.exception.*;
import com.sprint.project.patientAppointment.repository.*;
import com.sprint.project.patientAppointment.Service.AppointmentService;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PhysicianRepository physicianRepo;

    @Autowired
    private NurseRepository nurseRepo;

    //  MAPPER 

    private AppointmentEntity mapToEntity(AppointmentRequestDTO dto) {

        AppointmentEntity entity = new AppointmentEntity();

        entity.setAppointmentId(dto.getAppointmentId());

        PatientEntity patient = patientRepo.findById(dto.getPatientSsn())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with SSN: " + dto.getPatientSsn()));
        entity.setPatient(patient);

        PhysicianEntity physician = physicianRepo.findById(dto.getPhysicianId())
                .orElseThrow(() -> new RuntimeException("Physician not found with ID: " + dto.getPhysicianId()));
        entity.setPhysician(physician);

        if (dto.getPrepNurseId() != null) {
            NurseEntity nurse = nurseRepo.findById(dto.getPrepNurseId())
                    .orElseThrow(() -> new RuntimeException("Nurse not found with ID: " + dto.getPrepNurseId()));
            entity.setPrepNurse(nurse);
        }

        entity.setStart(dto.getStarttime());
        entity.setEnd(dto.getEndtime());
        entity.setExaminationRoom(dto.getExaminationRoom());

        return entity;
    }

    private AppointmentResponseDTO mapToResponse(AppointmentEntity entity) {

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

    // CREATE 
    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {

        if (dto == null || dto.getAppointmentId() == null) {
            throw new InvalidAppointmentTimeException("Invalid appointment data");
        }

        if (repo.existsById(dto.getAppointmentId())) {
            throw new AppointmentConflictException("Appointment already exists with ID: " + dto.getAppointmentId());
        }

        validateTime(dto.getStarttime(), dto.getEndtime());

        // OPTIONAL: Overlap check
        if (repo.existsOverlappingAppointmentForPhysician(
                dto.getPhysicianId(),
                dto.getStarttime(),
                dto.getEndtime())) {
            throw new AppointmentConflictException("Overlapping appointment exists");
        }

        AppointmentEntity saved = repo.save(mapToEntity(dto));
        return mapToResponse(saved);
    }

    // GET 
    @Override
    public AppointmentResponseDTO getAppointmentById(Integer id) {

        AppointmentEntity entity = repo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + id));

        return mapToResponse(entity);
    }

    // GET ALL 
    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE 
    @Override
    public AppointmentResponseDTO updateAppointment(Integer id, AppointmentRequestDTO dto) {

        AppointmentEntity entity = repo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + id));

        validateTime(dto.getStarttime(), dto.getEndtime());

        entity.setStart(dto.getStarttime());
        entity.setEnd(dto.getEndtime());
        entity.setExaminationRoom(dto.getExaminationRoom());

        return mapToResponse(repo.save(entity));
    }

    // DELETE 
    @Override
    public void deleteAppointment(Integer id) {

        AppointmentEntity entity = repo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + id));

        repo.delete(entity);
    }

    //  GET BY PATIENT 
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Integer ssn) {

        if (!patientRepo.existsById(ssn)) {
            throw new PatientNotFoundException("Patient not found with SSN: " + ssn);
        }

        return repo.findByPatient_Ssn(ssn)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //  GET BY PHYSICIAN 
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPhysician(Integer physicianId) {

        return repo.findByPhysician_EmployeeId(physicianId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY DATE 
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date) {

        return repo.findByDate(date)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //  VALIDATION 
    private void validateTime(LocalDateTime start, LocalDateTime end) {

        if (start == null || end == null) {
            throw new InvalidAppointmentTimeException("Start or End time cannot be null");
        }

        if (!start.isBefore(end)) {
            throw new InvalidAppointmentTimeException("Start time must be before End time");
        }

        if (start.isBefore(LocalDateTime.now())) {
            throw new InvalidAppointmentTimeException("Cannot create appointment in the past");
        }
    }
}
