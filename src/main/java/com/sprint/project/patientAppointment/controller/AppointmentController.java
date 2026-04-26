package com.sprint.project.patientAppointment.controller;

import com.sprint.project.patientAppointment.dto.requestdto.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.dto.responsedto.AppointmentResponseDTO;
import com.sprint.project.patientAppointment.service.AppointmentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

     /**
     * Create a new appointment.
     * Validates time window and checks physician scheduling conflicts.
     */
    @PostMapping("/api/appointments")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @Valid @RequestBody AppointmentRequestDTO dto) {
        AppointmentResponseDTO created = appointmentService.createAppointment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/api/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
     /**
     * Retrieve a single appointment by its ID.
     */
    @GetMapping("/api/appointments/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(
            @PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }

     /**
     * Update an existing appointment.
     * Re-validates time window and physician conflict.
     */
    @PutMapping("/api/appointments/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable Integer appointmentId,
            @Valid @RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentId, dto));
    }

    /**
     * Delete an appointment by ID.
     */
    @DeleteMapping("/api/appointments/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }

     /**
     * Get all appointments for a specific patient (by SSN).
     */
    @GetMapping("/api/patients/{ssn}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(
            @PathVariable Integer ssn) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(ssn));
    }

     /**
     * Get all appointments assigned to a specific physician.
     */
    @GetMapping("/api/physicians/{employeeId}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPhysician(
            @PathVariable Integer employeeId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPhysician(employeeId));
    }
    
    @GetMapping("/api/appointments/by-date")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(appointmentService.getAppointmentsByDate(date));
    }
}
