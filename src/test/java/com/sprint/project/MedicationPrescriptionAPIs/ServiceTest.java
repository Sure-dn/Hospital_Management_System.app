package com.sprint.project.MedicationPrescriptionAPIs;   // ←←← CHANGE THIS LINE IF NEEDED

// NOTE FOR YOU (beginner-friendly):
// If you saved the file inside a folder called "service/test", 
// change the line above to:
// package com.sprint.project.MedicationPrescriptionAPIs.Service.test;
//
// Just match the folder path exactly (Eclipse is strict about this).

import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.MedicationRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.DTO.RequestDTO.PrescriptionRequestDTO;
import com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations.MedicationServiceImpl;
import com.sprint.project.MedicationPrescriptionAPIs.Service.Implementations.PrescriptionServiceImpl;
import com.sprint.project.MedicationPrescriptionAPIs.entity.MedicationEntity;
import com.sprint.project.MedicationPrescriptionAPIs.entity.PrescribesEntity;
import com.sprint.project.MedicationPrescriptionAPIs.repository.MedicationRepository;
import com.sprint.project.MedicationPrescriptionAPIs.repository.PrescribesRepository;
import com.sprint.project.patientAppointment.Entity.AppointmentEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.AppointmentRepository;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    // ==================== MEDICATION SERVICE TESTS ====================
    @Nested
    class MedicationServiceTests {

        @Mock
        private MedicationRepository medicationRepository;

        @InjectMocks
        private MedicationServiceImpl medicationService;

        private MedicationRequestDTO validDto;
        private MedicationEntity validEntity;

        @BeforeEach
        void setUpMedication() {
            validDto = new MedicationRequestDTO();
            validDto.setCode(100);
            validDto.setName("Aspirin");
            validDto.setBrand("Bayer");
            validDto.setDescription("Pain reliever");

            validEntity = new MedicationEntity();
            validEntity.setCode(100);
            validEntity.setName("Aspirin");
            validEntity.setBrand("Bayer");
            validEntity.setDescription("Pain reliever");
        }

        @Test
        void testCreateMedication_Success() {
            when(medicationRepository.save(any(MedicationEntity.class))).thenReturn(validEntity);
            MedicationRequestDTO result = medicationService.createMedication(validDto);
            assertNotNull(result);
            assertEquals("Aspirin", result.getName());
        }

        @Test
        void testCreateMedication_NameBlank_ThrowsException() {
            validDto.setName("   ");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> medicationService.createMedication(validDto));
            assertEquals("Medication name is required", ex.getMessage());
        }

        @Test
        void testGetAllMedications_Success() {
            when(medicationRepository.findAll()).thenReturn(List.of(validEntity));
            List<MedicationRequestDTO> result = medicationService.getAllMedications();
            assertEquals(1, result.size());
        }

        @Test
        void testGetMedicationByCode_Success() {
            when(medicationRepository.findById(100)).thenReturn(Optional.of(validEntity));
            MedicationRequestDTO result = medicationService.getMedicationByCode(100);
            assertNotNull(result);
        }

        @Test
        void testGetMedicationByCode_NotFound() {
            when(medicationRepository.findById(999)).thenReturn(Optional.empty());
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> medicationService.getMedicationByCode(999));
            assertEquals("Medication not found", ex.getMessage());
        }

        @Test
        void testUpdateMedication_Success() {
            when(medicationRepository.findById(100)).thenReturn(Optional.of(validEntity));
            when(medicationRepository.save(any())).thenReturn(validEntity);

            MedicationRequestDTO updateDto = new MedicationRequestDTO();
            updateDto.setName("Updated Aspirin");
            MedicationRequestDTO result = medicationService.updateMedication(100, updateDto);
            assertEquals("Updated Aspirin", result.getName());
        }

        @Test
        void testUpdateMedication_NotFound() {
            when(medicationRepository.findById(999)).thenReturn(Optional.empty());
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> medicationService.updateMedication(999, new MedicationRequestDTO()));
            assertEquals("Medication not found", ex.getMessage());
        }

        @Test
        void testDeleteMedication_Success() {
            when(medicationRepository.existsById(100)).thenReturn(true);
            doNothing().when(medicationRepository).deleteById(100);
            assertDoesNotThrow(() -> medicationService.deleteMedication(100));
        }

        @Test
        void testDeleteMedication_NotFound() {
            when(medicationRepository.existsById(999)).thenReturn(false);
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> medicationService.deleteMedication(999));
            assertEquals("Medication not found", ex.getMessage());
        }
    }

    // ==================== PRESCRIPTION SERVICE TESTS ====================
    @Nested
    class PrescriptionServiceTests {

        @Mock private PrescribesRepository prescribesRepository;
        @Mock private PhysicianRepository physicianRepository;
        @Mock private PatientRepository patientRepository;
        @Mock private MedicationRepository medicationRepository;
        @Mock private AppointmentRepository appointmentRepository;

        @InjectMocks
        private PrescriptionServiceImpl prescriptionService;

        private PrescriptionRequestDTO validDto;
        private PhysicianEntity physician;
        private PatientEntity patient;
        private MedicationEntity medication;
        private AppointmentEntity appointment;
        private PrescribesEntity validEntity;

        @BeforeEach
        void setUpPrescription() {
            validDto = new PrescriptionRequestDTO();
            validDto.setPhysicianId(1);
            validDto.setPatientSsn(100000001);
            validDto.setMedicationCode(1);
            validDto.setAppointmentId(13216584);
            validDto.setDate(LocalDateTime.now());
            validDto.setDose("1 tablet");

            physician = new PhysicianEntity(); physician.setEmployeeId(1);
            patient = new PatientEntity(); patient.setSsn(100000001);
            medication = new MedicationEntity(); medication.setCode(1);
            appointment = new AppointmentEntity(); appointment.setAppointmentId(13216584);

            validEntity = new PrescribesEntity();
            validEntity.setPhysician(physician);
            validEntity.setPatient(patient);
            validEntity.setMedication(medication);
            validEntity.setDate(validDto.getDate());
            validEntity.setDose("1 tablet");
        }

        @Test
        void testCreatePrescription_WithDTO_Success() {
            when(physicianRepository.findById(1)).thenReturn(Optional.of(physician));
            when(patientRepository.findById(100000001)).thenReturn(Optional.of(patient));
            when(medicationRepository.findById(1)).thenReturn(Optional.of(medication));
            when(appointmentRepository.findById(13216584)).thenReturn(Optional.of(appointment));
            when(prescribesRepository.save(any())).thenReturn(validEntity);

            PrescribesEntity result = prescriptionService.createPrescription(validDto);
            assertNotNull(result);
        }

        @Test
        void testCreatePrescription_PhysicianNotFound() {
            when(physicianRepository.findById(999)).thenReturn(Optional.empty());
            validDto.setPhysicianId(999);
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validDto));
        }

        @Test
        void testCreatePrescription_PatientNotFound() {
            when(physicianRepository.findById(anyInt())).thenReturn(Optional.of(physician));
            when(patientRepository.findById(999999)).thenReturn(Optional.empty());
            validDto.setPatientSsn(999999);
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validDto));
        }

        @Test
        void testCreatePrescription_MedicationNotFound() {
            when(physicianRepository.findById(anyInt())).thenReturn(Optional.of(physician));
            when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
            when(medicationRepository.findById(999)).thenReturn(Optional.empty());
            validDto.setMedicationCode(999);
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validDto));
        }

        @Test
        void testCreatePrescription_AppointmentNotFound() {
            when(physicianRepository.findById(anyInt())).thenReturn(Optional.of(physician));
            when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
            when(medicationRepository.findById(anyInt())).thenReturn(Optional.of(medication));
            when(appointmentRepository.findById(999)).thenReturn(Optional.empty());
            validDto.setAppointmentId(999);
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validDto));
        }

        @Test
        void testCreatePrescription_WithEntity_Success() {
            when(prescribesRepository.save(any())).thenReturn(validEntity);
            PrescribesEntity result = prescriptionService.createPrescription(validEntity);
            assertNotNull(result);
        }

        @Test
        void testCreatePrescription_InvalidDose() {
            validEntity.setDose("   ");
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validEntity));
        }

        @Test
        void testCreatePrescription_NullDate() {
            validEntity.setDate(null);
            assertThrows(IllegalArgumentException.class, () -> prescriptionService.createPrescription(validEntity));
        }

        @Test
        void testGetAllPrescriptions_Success() {
            when(prescribesRepository.findAll()).thenReturn(List.of(validEntity));
            List<PrescribesEntity> result = prescriptionService.getAllPrescriptions();
            assertEquals(1, result.size());
        }

        @Test
        void testGetPrescriptionsByPatient_Success() {
            when(prescribesRepository.findByPatientSsn(100000001)).thenReturn(List.of(validEntity));
            List<PrescribesEntity> result = prescriptionService.getPrescriptionsByPatient(100000001);
            assertEquals(1, result.size());
        }

        @Test
        void testGetPrescriptionsByPhysician_Success() {
            when(prescribesRepository.findByPhysicianEmployeeId(1)).thenReturn(List.of(validEntity));
            List<PrescribesEntity> result = prescriptionService.getPrescriptionsByPhysician(1);
            assertEquals(1, result.size());
        }

        @Test
        void testGetPrescriptionsByDateRange_Success() {
            LocalDateTime from = LocalDateTime.now().minusDays(1);
            LocalDateTime to = LocalDateTime.now().plusDays(1);
            when(prescribesRepository.findByDateBetween(from, to)).thenReturn(List.of(validEntity));
            List<PrescribesEntity> result = prescriptionService.getPrescriptionsByDateRange(from, to);
            assertEquals(1, result.size());
        }

        @Test
        void testGetPrescriptionsByMedication_Success() {
            when(prescribesRepository.findByMedicationCode(1)).thenReturn(List.of(validEntity));
            List<PrescribesEntity> result = prescriptionService.getPrescriptionsByMedication(1);
            assertEquals(1, result.size());
        }
    }
}