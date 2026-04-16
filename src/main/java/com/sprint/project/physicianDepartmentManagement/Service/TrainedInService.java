package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInEntity;

public interface TrainedInService {
	 /**
     * Add a training/certification for a physician.
     * @throws ResourceNotFoundException if physician or procedure not found
     * @throws DuplicateResourceException if the training record already exists
     * @throws InvalidOperationException if certificationExpiry is in the past
     */
    TrainedInEntity addTraining(Integer physicianId, TrainedInEntity training);

    /**
     * Get all trainings for a physician.
     * @throws ResourceNotFoundException if physician not found
     */
    List<TrainedInEntity> getTrainingsByPhysician(Integer physicianId);

    /**
     * Get all valid (non-expired) trainings for a physician.
     * @throws ResourceNotFoundException if physician not found
     */
    List<TrainedInEntity> getValidTrainingsByPhysician(Integer physicianId);

}
