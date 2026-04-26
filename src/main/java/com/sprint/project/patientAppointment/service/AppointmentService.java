package com.sprint.project.patientAppointment.service;

import com.sprint.project.patientAppointment.dto.requestdto.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.dto.responsedto.AppointmentResponseDTO;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

	// CREATE
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);

    //  GET ONE
    AppointmentResponseDTO getAppointmentById(Integer id);

    // GET ALL
    List<AppointmentResponseDTO> getAllAppointments();

    //  UPDATE
    AppointmentResponseDTO updateAppointment(Integer id, AppointmentRequestDTO dto);

    // DELETE
    void deleteAppointment(Integer id);

    //  GET BY PATIENT
    List<AppointmentResponseDTO> getAppointmentsByPatient(Integer ssn);

    //  GET BY PHYSICIAN
    List<AppointmentResponseDTO> getAppointmentsByPhysician(Integer physicianId);

    // GET BY DATE
    List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date);
}
