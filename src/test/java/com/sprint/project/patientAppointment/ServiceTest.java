package com.sprint.project.patientAppointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import com.sprint.project.patientAppointment.DTO.RequestDTO.AppointmentRequestDTO;
import com.sprint.project.patientAppointment.DTO.ResponseDTO.AppointmentResponseDTO;
import com.sprint.project.patientAppointment.Service.Implementation.AppointmentServiceImpl;
import com.sprint.project.patientAppointment.entity.*;
import com.sprint.project.patientAppointment.repository.*;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.nurseoncallroom.repository.NurseRepository;
import com.sprint.project.patientAppointment.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private AppointmentRepository repo;

    @Mock
    private PatientRepository patientRepo;

    @Mock
    private PhysicianRepository physicianRepo;

    @Mock
    private NurseRepository nurseRepo;

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
        physician.setName("Dr John");
    }

    //  POSITIVE

    @Test
    void TC_P_01_createAppointment_success() {

        when(repo.existsById(1)).thenReturn(false);
        when(repo.existsOverlappingAppointmentForPhysician(any(), any(), any())).thenReturn(false);when(patientRepo.findById(101)).thenReturn(Optional.of(patient));
        when(physicianRepo.findById(201)).thenReturn(Optional.of(physician));
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));

        AppointmentResponseDTO result = service.createAppointment(dto);

        assertNotNull(result);
        assertEquals(1, result.getAppointmentId());
    }

    @Test
    void TC_P_02_getAppointmentById_success() {

        when(repo.findById(1)).thenReturn(Optional.of(mockEntity()));

        assertNotNull(service.getAppointmentById(1));
    }

    @Test
    void TC_P_03_getAllAppointments_success() {

        when(repo.findAll()).thenReturn(Arrays.asList(mockEntity()));

        assertEquals(1, service.getAllAppointments().size());
    }

    @Test
    void TC_P_04_updateAppointment_success() {

        when(repo.findById(1)).thenReturn(Optional.of(mockEntity()));
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));

        assertNotNull(service.updateAppointment(1, dto));
    }

    @Test
    void TC_P_05_deleteAppointment_success() {

        when(repo.findById(1)).thenReturn(Optional.of(mockEntity()));

        assertDoesNotThrow(() -> service.deleteAppointment(1));
    }

    //  NEGATIVE

    @Test
    void TC_N_01_duplicateId_shouldThrow() {

        when(repo.existsById(1)).thenReturn(true);

        assertThrows(AppointmentConflictException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_02_nullId_shouldThrow() {

        dto.setAppointmentId(null);

        assertThrows(InvalidAppointmentTimeException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_03_patientNotFound_shouldThrow() {

        when(repo.existsById(1)).thenReturn(false);
        when(repo.existsOverlappingAppointmentForPhysician(any(), any(), any())).thenReturn(false);
        when(patientRepo.findById(101)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_04_physicianNotFound_shouldThrow() {

        when(repo.existsById(1)).thenReturn(false);
        when(repo.existsOverlappingAppointmentForPhysician(any(), any(), any())).thenReturn(false);
        when(patientRepo.findById(101)).thenReturn(Optional.of(patient));
        when(physicianRepo.findById(201)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_N_05_getById_notFound_shouldThrow() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> service.getAppointmentById(1));
    }

    @Test
    void TC_N_06_delete_notFound_shouldThrow() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> service.deleteAppointment(1));
    }

    @Test
    void TC_N_07_patientAppointments_notFound_shouldThrow() {

        when(patientRepo.existsById(101)).thenReturn(false);

        assertThrows(PatientNotFoundException.class,
                () -> service.getAppointmentsByPatient(101));
    }

    //  EDGE CASES

    @Test
    void TC_E_01_startEqualsEnd_shouldThrow() {

        dto.setEndtime(dto.getStarttime());

        assertThrows(InvalidAppointmentTimeException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_E_02_pastTime_shouldThrow() {

        dto.setStarttime(LocalDateTime.now().minusDays(1));
        dto.setEndtime(LocalDateTime.now());

        assertThrows(InvalidAppointmentTimeException.class,
                () -> service.createAppointment(dto));
    }

    @Test
    void TC_E_03_nullTime_shouldThrow() {

        dto.setStarttime(null);

        assertThrows(InvalidAppointmentTimeException.class,
                () -> service.createAppointment(dto));
    }

    //  HELPER

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