package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Exception.*;
import com.sprint.project.patientAppointment.Repository.AppointmentRepository;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.patientAppointment.Service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    // ✅ CREATE
    @Override
    public AppointmentEntity createAppointment(AppointmentEntity appointment) {

        if (appointment.getAppointmentId() == null) {
            throw new BadRequestException("Appointment ID cannot be null");
        }

        if (repo.existsById(appointment.getAppointmentId())) {
            throw new DuplicateResourceException("Appointment already exists with ID: "
                    + appointment.getAppointmentId());
        }

        validateTime(appointment.getStart(), appointment.getEnd());

        return repo.save(appointment);
    }

    // ✅ READ ONE
    @Override
    public AppointmentEntity getAppointmentById(Integer id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));
    }

    // ✅ READ ALL
    @Override
    public List<AppointmentEntity> getAllAppointments() {
        return repo.findAll();
    }

    // ✅ UPDATE
    @Override
    public AppointmentEntity updateAppointment(Integer id, AppointmentEntity appointment) {

        AppointmentEntity existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));

        validateTime(appointment.getStart(), appointment.getEnd());

        existing.setStart(appointment.getStart());
        existing.setEnd(appointment.getEnd());
        existing.setExaminationRoom(appointment.getExaminationRoom());
        existing.setPatient(appointment.getPatient());
        existing.setPhysician(appointment.getPhysician());
        existing.setPrepNurse(appointment.getPrepNurse());

        return repo.save(existing);
    }

    // ✅ DELETE
    @Override
    public void deleteAppointment(Integer id) {

        AppointmentEntity existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found with ID: " + id));

        repo.delete(existing);
    }

    // ✅ GET BY PATIENT
    @Override
    public List<AppointmentEntity> getAppointmentsByPatient(Integer ssn) {

        if (!patientRepo.existsById(ssn)) {
            throw new ResourceNotFoundException("Patient not found with SSN: " + ssn);
        }

        return repo.findByPatient_Ssn(ssn);   // ✅ FIXED
    }

    // ✅ GET BY PHYSICIAN
    @Override
    public List<AppointmentEntity> getAppointmentsByPhysician(Integer physicianId) {

        if (physicianId == null || physicianId <= 0) {
            throw new BadRequestException("Invalid Physician ID");
        }

        return repo.findByPhysician_EmployeeId(physicianId);  // ✅ FIXED
    }

    // ✅ GET BY DATE
    @Override
    public List<AppointmentEntity> getAppointmentsByDate(LocalDate date) {

        if (date == null) {
            throw new BadRequestException("Date cannot be null");
        }

        return repo.findByDate(date); // must match repo
    }

    // ✅ VALIDATION
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