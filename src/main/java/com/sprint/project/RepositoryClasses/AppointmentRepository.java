package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
}
