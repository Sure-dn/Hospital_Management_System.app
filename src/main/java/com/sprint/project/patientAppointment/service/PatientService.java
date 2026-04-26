package com.sprint.project.patientAppointment.service;


import com.sprint.project.patientAppointment.dto.requestdto.PatientRequestDTO;
import com.sprint.project.patientAppointment.dto.responsedto.PatientResponseDTO;


import java.util.List;

public interface PatientService {

	PatientResponseDTO createPatient(PatientRequestDTO requestDTO);
    PatientResponseDTO getPatientById(Integer ssn);
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO updatePatient(Integer ssn, PatientRequestDTO requestDTO);
    void deletePatient(Integer ssn);
    Integer getPCP(Integer ssn);

    PatientResponseDTO updatePCP(Integer ssn, Integer physicianId);
}



