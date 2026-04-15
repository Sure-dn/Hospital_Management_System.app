package com.sprint.project.MedicationPrescriptionAPIs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.MedicationPrescriptionAPIs.Controller.MedicationController;



public interface MedicationRepository extends JpaRepository<MedicationController,Integer>  {

}
