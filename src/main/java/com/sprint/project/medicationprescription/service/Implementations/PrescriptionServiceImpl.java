package com.sprint.project.medicationprescription.service.Implementations;


import com.sprint.project.medicationprescription.dto.requestdto.PrescriptionRequestDTO;
import com.sprint.project.medicationprescription.entity.PrescribesEntity;
import com.sprint.project.medicationprescription.repository.PrescribesRepository;
import com.sprint.project.medicationprescription.service.PrescribesService;

import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.repository.PatientRepository;
import com.sprint.project.patientAppointment.entity.AppointmentEntity;
import com.sprint.project.patientAppointment.repository.AppointmentRepository;
import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.repository.MedicationRepository;

import com.sprint.project.medicationprescription.dto.requestdto.PrescriptionRequestDTO;
import com.sprint.project.medicationprescription.entity.PrescribesEntity;
import com.sprint.project.medicationprescription.repository.PrescribesRepository;
import com.sprint.project.medicationprescription.service.PrescribesService;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.patientAppointment.entity.AppointmentEntity;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.repository.AppointmentRepository;
import com.sprint.project.patientAppointment.repository.PatientRepository;
import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.repository.MedicationRepository;

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