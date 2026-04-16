package com.sprint.project.patientAppointment.Service;

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    AppointmentEntity createAppointment(AppointmentEntity appointment);

    AppointmentEntity getAppointmentById(Integer id);

    List<AppointmentEntity> getAllAppointments();

    AppointmentEntity updateAppointment(Integer id, AppointmentEntity appointment);

    void deleteAppointment(Integer id);

    List<AppointmentEntity> getAppointmentsByPatient(Integer ssn);

    List<AppointmentEntity> getAppointmentsByPhysician(Integer physicianId);

    List<AppointmentEntity> getAppointmentsByDate(LocalDate date);
}
