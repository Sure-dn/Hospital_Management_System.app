package com.sprint.project.patientAppointment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.patientAppointment.Entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

}

