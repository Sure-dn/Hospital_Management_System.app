package com.sprint.project.patientAppointment.Service.Implementation;

import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Exception.*;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.patientAppointment.Service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repo;

    @Override
    public PatientEntity createPatient(PatientEntity patient) {

        if (patient.getSsn() == null || patient.getSsn() <= 0) {
            throw new BadRequestException("Invalid SSN");
        }

        if (repo.existsById(patient.getSsn())) {
            throw new DuplicateResourceException("Patient already exists with SSN: " + patient.getSsn());
        }

        return repo.save(patient);
    }

    @Override
    public PatientEntity getPatientById(Integer ssn) {

        return repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));
    }

    @Override
    public List<PatientEntity> getAllPatients() {
        return repo.findAll();
    }

    @Override
    public PatientEntity updatePatient(Integer ssn, PatientEntity patient) {

        PatientEntity existing = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        existing.setName(patient.getName());
        existing.setAddress(patient.getAddress());
        existing.setPhone(patient.getPhone());
        existing.setInsuranceId(patient.getInsuranceId());
        existing.setPcp(patient.getPcp());

        return repo.save(existing);
    }

    @Override
    public void deletePatient(Integer ssn) {

        PatientEntity existing = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        repo.delete(existing);
    }

    @Override
    public Integer getPCP(Integer ssn) {

        return repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn))
                .getPcp();
    }

    @Override
    public PatientEntity updatePCP(Integer ssn, Integer physicianId) {

        if (physicianId == null || physicianId <= 0) {
            throw new BadRequestException("Invalid Physician ID");
        }

        PatientEntity patient = repo.findById(ssn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with SSN: " + ssn));

        patient.setPcp(physicianId);

        return repo.save(patient);
    }
}