package com.sprint.project.Treatment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import com.sprint.project.Treatment.DTO.ProceduresRequestDTO;
import com.sprint.project.Treatment.DTO.StayRequestDTO;
import com.sprint.project.Treatment.DTO.UndergoesRequestDTO;
import com.sprint.project.Treatment.Entity.*;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.Treatment.Repository.StayRepository;
import com.sprint.project.Treatment.Service.*;
import com.sprint.project.exception.*;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ProceduresRepository proceduresRepository;

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private ProceduresService proceduresService;

    @Autowired
    private StayService stayService;

    @Autowired
    private UndergoesService undergoesService;

    // =========================================
    // ✅ PROCEDURES SERVICE TEST
    // =========================================

    @Test
    void testAddProcedure_Success() {

        ProceduresRequestDTO p = new ProceduresRequestDTO();
        p.setCode(111);
        p.setName("Test Procedure today");
        p.setCost(500111.0);

        ProceduresRequestDTO saved = proceduresService.addProcedure(p);

        assertThat(saved).isNotNull();
    }

    @Test
    void testAddProcedure_Duplicate() {

        ProceduresRequestDTO p = new ProceduresRequestDTO();
        p.setCode(1); // already exists

        assertThrows(DuplicateResourceException.class, () -> {
            proceduresService.addProcedure(p);
        });
    }

    @Test
    void testGetProcedure_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            proceduresService.getProcedureById(99999);
        });
    }

    @Test
    void testGetAllProcedures() {
        List<ProceduresRequestDTO> list = proceduresService.getAllProcedures();
        assertThat(list).isNotEmpty();
    }

    // =========================================
    // ✅ STAY SERVICE TEST
    // =========================================

    @Test
    void testAdmitPatient_InvalidDate() {

        StayRequestDTO stay = new StayRequestDTO();
        stay.setStayId(999);
        stay.setStayStart(LocalDateTime.now().plusDays(1)); // future date

        assertThrows(BadRequestException.class, () -> {
            stayService.admitPatient(stay);
        });
    }

    @Test
    void testGetStay_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            stayService.getStayById(99999);
        });
    }

    @Test
    void testGetAllStays() {

        PatientEntity patient = patientRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No patient found"));

        RoomEntity room = roomRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No room found"));

        StayRequestDTO stay = new StayRequestDTO();
        stay.setStayId(999);
        stay.setPatientId(patient.getSsn());
        stay.setRoomId(room.getRoomNumber());
        stay.setStayStart(LocalDateTime.now());

        stayService.admitPatient(stay);

        List<StayEntity> list = stayService.getAllStays();

        assertThat(list).isNotEmpty();
    }

    // =========================================
    // ✅ UNDERGOES SERVICE TEST
    // =========================================

    @Test
    void testGetAllTreatments() {

        PatientEntity patient = patientRepository.findAll()
                .stream().findFirst().orElseThrow();

        ProceduresEntity procedure = proceduresRepository.findAll()
                .stream().findFirst().orElseThrow();

        StayEntity stay = stayRepository.findAll()
                .stream().findFirst().orElseThrow();

        PhysicianEntity physician = physicianRepository.findAll()
                .stream().findFirst().orElseThrow();

        NurseEntity nurse = nurseRepository.findAll()
                .stream().findFirst().orElseThrow();

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(patient.getSsn());
        dto.setProcedureId(procedure.getCode());
        dto.setStayId(stay.getStayId());
        dto.setDateUndergoes(LocalDateTime.now());
        dto.setPhysicianId(physician.getEmployeeId());
        dto.setNurseId(nurse.getEmployeeId());

        undergoesService.assignTreatment(dto);

        List<UndergoesRequestDTO> list = undergoesService.getAllTreatments();

        assertThat(list).isNotEmpty();
    }

    @Test
    void testGetTreatmentsByPatient() {
        List<UndergoesRequestDTO> list = undergoesService.getTreatmentByPatient(100000001);
        assertThat(list).isNotNull();
    }

    @Test
    void testGetTreatmentsByStay() {
        List<UndergoesRequestDTO> list = undergoesService.getTreatmentByStay(3215);
        assertThat(list).isNotNull();
    }

    @Test
    void testDeleteTreatment_NotFound() {

        UndergoesId id = new UndergoesId(
                999,
                999,
                999,
                LocalDateTime.now()
        );

        assertThrows(ResourceNotFoundException.class, () -> {
            undergoesService.deleteTreatment(id);
        });
    }
}