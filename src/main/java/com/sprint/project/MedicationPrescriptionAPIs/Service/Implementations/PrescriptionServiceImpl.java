package com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations;

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.PrescriptionRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Repository.PrescribesRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Service.PrescribesService;

import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Repository.AppointmentRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.MedicationEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Repository.MedicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescribesService {

    @Autowired
    private PrescribesRepository prescribesRepository;

    @Autowired
    private PhysicianRepository physicianRepository;      // from physician module
    @Autowired
    private PatientRepository patientRepository;          // from patient module
    @Autowired
    private MedicationRepository medicationRepository;    // current module
    @Autowired
    private AppointmentRepository appointmentRepository;  // from patient module (optional)

    @Override
    public PrescribesEntity createPrescription(PrescriptionRequestDTO dto) {
        // Fetch related entities (required because PrescribesEntity uses @ManyToOne objects)
        PhysicianEntity physician = physicianRepository.findById(dto.getPhysicianId())
                .orElseThrow(() -> new IllegalArgumentException("Physician not found with ID: " + dto.getPhysicianId()));

        PatientEntity patient = patientRepository.findById(dto.getPatientSsn())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with SSN: " + dto.getPatientSsn()));

        MedicationEntity medication = medicationRepository.findById(dto.getMedicationCode())
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with code: " + dto.getMedicationCode()));

        AppointmentEntity appointment = null;
        if (dto.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + dto.getAppointmentId()));
        }

        // Build entity
        PrescribesEntity entity = new PrescribesEntity();
        entity.setPhysician(physician);
        entity.setPatient(patient);
        entity.setMedication(medication);
        entity.setDate(dto.getDate());
        entity.setAppointment(appointment);
        entity.setDose(dto.getDose());

        // Reuse existing business logic + save
        return createPrescription(entity);
    }

    @Override
    public PrescribesEntity createPrescription(PrescribesEntity prescription) {
        if (prescription.getDose() == null || prescription.getDose().isBlank()) {
            throw new IllegalArgumentException("Dose cannot be empty");
        }
        if (prescription.getDate() == null) {
            throw new IllegalArgumentException("Date is required");
        }
        return prescribesRepository.save(prescription);
    }

    @Override
    public List<PrescribesEntity> getAllPrescriptions() {
        return prescribesRepository.findAll();
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByPatient(Integer ssn) {
        return prescribesRepository.findByPatientSsn(ssn);
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByPhysician(Integer employeeId) {
        return prescribesRepository.findByPhysicianEmployeeId(employeeId);
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByDateRange(LocalDateTime from, LocalDateTime to) {
        return prescribesRepository.findByDateBetween(from, to);
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByMedication(Integer code) {
        return prescribesRepository.findByMedicationCode(code);
    }
}