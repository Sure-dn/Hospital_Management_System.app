package com.sprint.project.medicationprescription.service.implementations;

// ✅ EXCEPTIONS
import com.sprint.project.medicationprescription.exception.*;

// ✅ SPRING
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ✅ DTO
import com.sprint.project.medicationprescription.dto.requestdto.PrescriptionRequestDTO;

// ✅ ENTITIES
import com.sprint.project.medicationprescription.entity.MedicationEntity;
import com.sprint.project.medicationprescription.entity.PrescribesEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.entity.AppointmentEntity;

// ✅ REPOSITORIES
import com.sprint.project.medicationprescription.repository.MedicationRepository;
import com.sprint.project.medicationprescription.repository.PrescribesRepository;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.patientAppointment.repository.PatientRepository;
import com.sprint.project.patientAppointment.repository.AppointmentRepository;

// ✅ JAVA
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements com.sprint.project.medicationprescription.service.PrescribesService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private PrescribesRepository prescribesRepository;

    // ✅ CREATE USING DTO
    @Override
    public PrescribesEntity createPrescription(PrescriptionRequestDTO dto) {

        PhysicianEntity physician = physicianRepository.findById(dto.getPhysicianId())
                .orElseThrow(() -> new PrescribesNotFoundException(
                        "Physician not found with ID: " + dto.getPhysicianId()));

        PatientEntity patient = patientRepository.findById(dto.getPatientSsn())
                .orElseThrow(() -> new PrescribesNotFoundException(
                        "Patient not found with SSN: " + dto.getPatientSsn()));

        MedicationEntity medication = medicationRepository.findById(dto.getMedicationCode())
                .orElseThrow(() -> new MedicationNotFoundException(dto.getMedicationCode()));

        AppointmentEntity appointment = null;
        if (dto.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new PrescribesNotFoundException(
                            "Appointment not found with ID: " + dto.getAppointmentId()));
        }

        PrescribesEntity entity = new PrescribesEntity();
        entity.setPhysician(physician);
        entity.setPatient(patient);
        entity.setMedication(medication);
        entity.setDate(dto.getDate());
        entity.setAppointment(appointment);
        entity.setDose(dto.getDose());

        return prescribesRepository.save(entity); // ✅ FIXED (no recursion)
    }

    // ✅ CREATE USING ENTITY
    @Override
    public PrescribesEntity createPrescription(PrescribesEntity prescription) {

        if (prescription.getDose() == null || prescription.getDose().isBlank()) {
            throw new InvalidDoseException("Dose cannot be empty");
        }

        if (prescription.getDate() == null) {
            throw new InvalidMedicationDataException("Date is required");
        }

        return prescribesRepository.save(prescription);
    }

    // ✅ GET ALL
    @Override
    public List<PrescribesEntity> getAllPrescriptions() {
        return prescribesRepository.findAll();
    }

    // ✅ GET BY PATIENT
    @Override
    public List<PrescribesEntity> getPrescriptionsByPatient(Integer ssn) {
        return prescribesRepository.findByPatientSSN(ssn);
    }

    // ✅ GET BY PHYSICIAN
    @Override
    public List<PrescribesEntity> getPrescriptionsByPhysician(Integer employeeId) {
        return prescribesRepository.findByPhysicianEmployeeId(employeeId);
    }

    // ✅ GET BY DATE RANGE
    @Override
    public List<PrescribesEntity> getPrescriptionsByDateRange(LocalDateTime from, LocalDateTime to) {
        return prescribesRepository.findByDateBetween(from, to);
    }

    // ✅ GET BY MEDICATION
    @Override
    public List<PrescribesEntity> getPrescriptionsByMedication(Integer code) {
        return prescribesRepository.findByMedicationCode(code);
    }
}