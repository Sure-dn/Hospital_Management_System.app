package com.sprint.project.patientAppointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import com.sprint.project.patientAppointment.DTO.RequestDTO.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.AppointmentResponseDTO;
import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.AppointmentRepository;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.patientAppointment.Service.Implementation.AppointmentServiceImpl;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientAndAppointmentValidationTest {

    @Mock
    private AppointmentRepository appointmentRepo;

    @Mock
    private PatientRepository patientRepo;

    @Mock
    private PhysicianRepository physicianRepo;

    @InjectMocks
    private AppointmentServiceImpl service;

    private AppointmentRequestDTO dto;
    private PatientEntity patient;
    private PhysicianEntity physician;

    @BeforeEach
    void setUp() {

        dto = new AppointmentRequestDTO();
        dto.setAppointmentId(1);
        dto.setPatientSsn(101);
        dto.setPhysicianId(201);
        dto.setStarttime(LocalDateTime.now().plusDays(1));
        dto.setEndtime(LocalDateTime.now().plusDays(1).plusHours(1));
        dto.setExaminationRoom("Room-1");

        patient = new PatientEntity();
        patient.setSsn(101);
        patient.setName("Patient A");

        physician = new PhysicianEntity();
        physician.setEmployeeId(201);
        physician.setName("Dr. John");
    }

    // ================= POSITIVE =================

    @Test
    void TC_P_01_createAppointment_validData_success() {

        when(patientRepo.findById(101)).thenReturn(Optional.of(patient));
        when(physicianRepo.findById(201)).thenReturn(Optional.of(physician));
        when(appointmentRepo.existsById(1)).thenReturn(false);
        when(appointmentRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        AppointmentResponseDTO result = service.createAppointment(dto);

        assertNotNull(result);
        assertEquals(1, result.getAppointmentId());
    }

    @Test
    void TC_P_02_getAppointmentById_validId_success() {

        when(appointmentRepo.findById(1)).thenReturn(Optional.of(mockEntity()));

        AppointmentResponseDTO result = service.getAppointmentById(1);

        assertNotNull(result);
        assertEquals(1, result.getAppointmentId());
    }

    @Test
    void TC_P_03_getAllAppointments_success() {

        when(appointmentRepo.findAll()).thenReturn(Arrays.asList(mockEntity()));

        assertEquals(1, service.getAllAppointments().size());
    }

    @Test
    void TC_P_04_updateAppointment_validData_success() {

        when(appointmentRepo.findById(1)).thenReturn(Optional.of(mockEntity()));
        when(appointmentRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        AppointmentResponseDTO result = service.updateAppointment(1, dto);

        assertNotNull(result);
    }

    @Test
    void TC_P_05_deleteAppointment_validId_success() {

        when(appointmentRepo.findById(1)).thenReturn(Optional.of(mockEntity()));

        assertDoesNotThrow(() -> service.deleteAppointment(1));
    }

    // ================= NEGATIVE =================

    @Test
    void TC_N_01_createAppointment_duplicateId_shouldThrow() {

        when(appointmentRepo.existsById(1)).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_02_createAppointment_nullId_shouldThrow() {

        dto.setAppointmentId(null);

        assertThrows(BadRequestException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_03_createAppointment_patientNotFound_shouldThrow() {

        when(patientRepo.findById(101)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_04_createAppointment_physicianNotFound_shouldThrow() {

        when(patientRepo.findById(101)).thenReturn(Optional.of(patient));
        when(physicianRepo.findById(201)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_05_getAppointmentById_invalidId_shouldThrow() {

        when(appointmentRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getAppointmentById(1));
    }

    @Test
    void TC_N_06_deleteAppointment_notFound_shouldThrow() {

        when(appointmentRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.deleteAppointment(1));
    }

    @Test
    void TC_N_07_getAppointmentsByPatient_notFound_shouldThrow() {

        when(patientRepo.existsById(101)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.getAppointmentsByPatient(101));
    }

    // ================= EDGE =================

    @Test
    void TC_E_01_createAppointment_startEqualsEnd_shouldThrow() {

        dto.setEndtime(dto.getStarttime());

        assertThrows(InvalidRequestException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_E_02_createAppointment_pastTime_shouldThrow() {

        dto.setStarttime(LocalDateTime.now().minusDays(1));
        dto.setEndtime(LocalDateTime.now());

        assertThrows(InvalidRequestException.class, () -> service.createAppointment(dto));
    }

    @Test
    void TC_E_03_createAppointment_nullTime_shouldThrow() {

        dto.setStarttime(null);

        assertThrows(BadRequestException.class, () -> service.createAppointment(dto));
    }

    // ================= HELPER =================

    private AppointmentEntity mockEntity() {

        AppointmentEntity e = new AppointmentEntity();
        e.setAppointmentId(1);
        e.setPatient(patient);
        e.setPhysician(physician);
        e.setStart(LocalDateTime.now().plusDays(1));
        e.setEnd(LocalDateTime.now().plusDays(1).plusHours(1));
        e.setExaminationRoom("Room-1");

        return e;
    }
}