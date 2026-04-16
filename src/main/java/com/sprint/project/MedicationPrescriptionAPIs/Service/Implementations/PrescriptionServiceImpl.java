package com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations;

import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Repository.PrescribesRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Service.Interfaces.PrescribesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescribesService {

    @Autowired
    private PrescribesRepository prescribesRepository;

    @Override
    public PrescribesEntity createPrescription(PrescribesEntity prescription) {
        // Business logic (entity already has @NotNull/@NotBlank validations)
        if (prescription.getDose() == null || prescription.getDose().isBlank()) {
            throw new IllegalArgumentException("Dose cannot be empty");
        }
        if (prescription.getDate() == null) {
            throw new IllegalArgumentException("Date is required");
        }
        // Associated entities (Physician, Patient, Medication) must be set before calling this
        return prescribesRepository.save(prescription);
    }

    @Override
    public List<PrescribesEntity> getAllPrescriptions() {
        return prescribesRepository.findAll();
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByPatient(Integer ssn) {
        return prescribesRepository.findAll().stream()
                .filter(p -> p.getPatient() != null && p.getPatient().getSsn() != null 
                          && p.getPatient().getSsn().equals(ssn))   // ← Change to getSSN() if your PatientEntity uses that
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByPhysician(Integer employeeId) {
        return prescribesRepository.findAll().stream()
                .filter(p -> p.getPhysician() != null && p.getPhysician().getEmployeeId() != null 
                          && p.getPhysician().getEmployeeId().equals(employeeId))  // ← Change to getEmployeeID() if needed
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByDateRange(LocalDateTime from, LocalDateTime to) {
        return prescribesRepository.findAll().stream()
                .filter(p -> p.getDate() != null 
                          && !p.getDate().isBefore(from) 
                          && !p.getDate().isAfter(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescribesEntity> getPrescriptionsByMedication(Integer code) {
        return prescribesRepository.findAll().stream()
                .filter(p -> p.getMedication() != null && p.getMedication().getCode() != null 
                          && p.getMedication().getCode().equals(code))
                .collect(Collectors.toList());
    }
}