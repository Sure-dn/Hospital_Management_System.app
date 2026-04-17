package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInId;
import com.sprint.project.physicianDepartmentManagement.Exception.DuplicateResourceException;
import com.sprint.project.physicianDepartmentManagement.Exception.InvalidOperationException;
import com.sprint.project.physicianDepartmentManagement.Exception.ResourceNotFoundException;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.TrainedInRepository;
import com.sprint.project.physicianDepartmentManagement.Service.TrainedInService;

@Service
public class TrainedInServiceImpl implements TrainedInService {

    private final TrainedInRepository trainedInRepository;
    private final PhysicianRepository physicianRepository;

    public TrainedInServiceImpl(TrainedInRepository trainedInRepository,
                                 PhysicianRepository physicianRepository) {
        this.trainedInRepository = trainedInRepository;
        this.physicianRepository = physicianRepository;
    }

    @Override
    public TrainedInEntity addTraining(Integer physicianId, TrainedInEntity training) {
        // Validate physician exists
        PhysicianEntity physician = physicianRepository.findById(physicianId)
            .orElseThrow(() -> new ResourceNotFoundException("Physician", "EmployeeID", physicianId));

        Integer treatmentCode = training.getTreatment().getCode();
        TrainedInId trainedInId = new TrainedInId(physicianId, treatmentCode);

        // Business Rule: No duplicate training record for same physician + procedure
        if (trainedInRepository.existsById(trainedInId)) {
            throw new DuplicateResourceException("Training",
                "PhysicianID + TreatmentCode", physicianId + "+" + treatmentCode);
        }

        // Business Rule: Certification expiry must be a future date
        if (training.getCertificationExpiry() != null
            && !training.getCertificationExpiry().isAfter(LocalDate.now())) {
            throw new InvalidOperationException(
                "CertificationExpiry must be a future date. Provided: " + training.getCertificationExpiry()
            );
        }

        training.setTrainedInId(trainedInId);
        training.setPhysician(physician);
        return trainedInRepository.save(training);
    }

    @Override
    public List<TrainedInEntity> getTrainingsByPhysician(Integer physicianId) {
        if (!physicianRepository.existsById(physicianId)) {
            throw new ResourceNotFoundException("Physician", "EmployeeID", physicianId);
        }
        return trainedInRepository.findByPhysician_EmployeeId(physicianId);
    }

    @Override
    public List<TrainedInEntity> getValidTrainingsByPhysician(Integer physicianId) {
        if (!physicianRepository.existsById(physicianId)) {
            throw new ResourceNotFoundException("Physician", "EmployeeID", physicianId);
        }
        return trainedInRepository.findValidTrainingsByPhysicianId(physicianId, LocalDate.now());
    }
}
