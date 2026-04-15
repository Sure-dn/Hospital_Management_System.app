package com.sprint.project.MedicationPrescriptionAPIs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.MedicationPrescriptionAPIs.Controller.PrescribesController;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesId;

public interface PrescribesRepository extends JpaRepository<PrescribesController,PrescribesId> {

}
