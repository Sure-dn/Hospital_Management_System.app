package com.sprint.project.MedicationPrescriptionAPIs.Service.Interfaces;



import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface PrescribesService {
    PrescribesEntity createPrescription(PrescribesEntity prescription);
    List<PrescribesEntity> getAllPrescriptions();
    List<PrescribesEntity> getPrescriptionsByPatient(Integer ssn);
    List<PrescribesEntity> getPrescriptionsByPhysician(Integer employeeId);
    List<PrescribesEntity> getPrescriptionsByDateRange(LocalDateTime from, LocalDateTime to);
    List<PrescribesEntity> getPrescriptionsByMedication(Integer code);
}