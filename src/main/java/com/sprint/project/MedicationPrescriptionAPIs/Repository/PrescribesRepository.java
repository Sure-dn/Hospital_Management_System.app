package com.sprint.project.MedicationPrescriptionAPIs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.Entity.PrescribesId;

public interface PrescribesRepository extends JpaRepository<PrescribesEntity ,PrescribesId> {

}
