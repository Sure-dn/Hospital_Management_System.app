package com.sprint.project.patientAppointment.Service;


import com.sprint.project.patientAppointment.Entity.PatientEntity;

import java.util.List;

public interface PatientService {

	PatientEntity createPatient(PatientEntity patient);
    PatientEntity getPatientById(Integer ssn);
    List<PatientEntity> getAllPatients();
    PatientEntity updatePatient(Integer ssn, PatientEntity patient);
    void deletePatient(Integer ssn);
    Integer getPCP(Integer ssn);

    PatientEntity updatePCP(Integer ssn, Integer physicianId);
}
