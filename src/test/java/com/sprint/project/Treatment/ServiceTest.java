package com.sprint.project.Treatment;

import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import com.sprint.project.Treatment.DTO.*;
import com.sprint.project.Treatment.Entity.*;
import com.sprint.project.Treatment.Repository.*;
import com.sprint.project.Treatment.Service.*;
import com.sprint.project.exception.*;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.repository.PatientRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired private PatientRepository patientRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private ProceduresRepository proceduresRepository;
    @Autowired private StayRepository stayRepository;

    @Autowired private ProceduresService proceduresService;
    @Autowired private StayService stayService;
    @Autowired private UndergoesService undergoesService;

    // ==========================
    // HELPERS (FIXED)
    // ==========================

    private PatientEntity createPatient(int id) {
        PatientEntity p = new PatientEntity();
        p.setSsn(id);
        p.setName("Patient " + id);
        p.setAddress("Chennai");        // ✅ REQUIRED
        p.setPhone("9876543210");       // ✅ REQUIRED
        p.setInsuranceId(1111);         // ✅ REQUIRED
        p.setPcp(1);                    // ✅ REQUIRED
        return patientRepository.save(p);
    }

    private RoomEntity createRoom(int id) {
        RoomEntity r = new RoomEntity();
        r.setRoomNumber(id);
        r.setType("GENERAL");        // ✅ REQUIRED
        r.setUnavailable(false);
        return roomRepository.save(r);
    }

    private ProceduresEntity createProcedure(int code) {
        ProceduresEntity p = new ProceduresEntity();
        p.setCode(code);
        p.setName("Procedure " + code);
        p.setCost(100.0);
        return proceduresRepository.save(p);
    }

    private StayEntity createStay(PatientEntity patient, RoomEntity room, int id) {
        StayEntity s = new StayEntity();
        s.setStayId(id);
        s.setPatient(patient);
        s.setRoom(room);
        s.setStayStart(LocalDateTime.now());
        return stayRepository.save(s);
    }

    // ==========================
    // PROCEDURE TESTS
    // ==========================
    @BeforeEach
    void cleanDB() {
        stayRepository.deleteAll();
        roomRepository.deleteAll();
        patientRepository.deleteAll();
    }
    @Test
    void testAddProcedure() {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(1);
        dto.setName("Test");
        dto.setCost(100.0);

        assertThat(proceduresService.addProcedure(dto)).isNotNull();
    }

    @Test
    void testAddProcedureDuplicate() {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(2);
        dto.setName("Test");
        dto.setCost(100.0);

        proceduresService.addProcedure(dto);

        assertThrows(DuplicateResourceException.class,
                () -> proceduresService.addProcedure(dto));
    }

    @Test
    void testGetProcedureById_NotFound() {
        assertThrows(ResourceNotFoundException.class,
                () -> proceduresService.getProcedureById(9999));
    }

    @Test
    void testGetAllProcedures() {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(3);
        dto.setName("A");
        dto.setCost(100.0);

        proceduresService.addProcedure(dto);

        assertThat(proceduresService.getAllProcedures()).isNotEmpty();
    }

    @Test
    void testDeleteProcedure() {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(4);
        dto.setName("del");
        dto.setCost(100.0);

        proceduresService.addProcedure(dto); // ✅ ONLY ONCE

        proceduresService.deleteProcedure(4);

        assertThrows(ResourceNotFoundException.class,
                () -> proceduresService.getProcedureById(4));
    }

    // ==========================
    // STAY TESTS
    // ==========================

    @Test
    void testAdmitPatient() {
        PatientEntity p = createPatient(100);
        RoomEntity r = createRoom(200);

        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(1);
        dto.setPatientId(p.getSsn());
        dto.setRoomId(r.getRoomNumber());
        dto.setStayStart(LocalDateTime.now());

        assertThat(stayService.admitPatient(dto)).isNotNull();
    }

    @Test
    void testAdmitPatient_InvalidDate() {
        PatientEntity p = createPatient(101);
        RoomEntity r = createRoom(201);

        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(2);
        dto.setPatientId(p.getSsn());
        dto.setRoomId(r.getRoomNumber());
        dto.setStayStart(LocalDateTime.now().plusDays(1));

        assertThrows(BadRequestException.class,
                () -> stayService.admitPatient(dto));
    }

    @Test
    void testGetAllStays() {
        PatientEntity p = createPatient(102);
        RoomEntity r = createRoom(202);

        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(3);
        dto.setPatientId(p.getSsn());
        dto.setRoomId(r.getRoomNumber());
        dto.setStayStart(LocalDateTime.now());

        stayService.admitPatient(dto);

        assertThat(stayService.getAllStays()).isNotEmpty();
    }

    @Test
    void testGetStayById_NotFound() {
        assertThrows(ResourceNotFoundException.class,
                () -> stayService.getStayById(9999));
    }

    @Test
    void testDeleteStay() {

        PatientEntity p = createPatient(103);
        RoomEntity r = createRoom(203);

        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(4);
        dto.setPatientId(p.getSsn());
        dto.setRoomId(r.getRoomNumber());
        dto.setStayStart(LocalDateTime.now());

        stayService.admitPatient(dto);

        // ✅ First delete → works
        stayService.deleteStay(4);

        // ✅ Second delete → should throw exception
        assertThrows(ResourceNotFoundException.class,
                () -> stayService.deleteStay(4));
    }

    // ==========================
    // UNDERGOES TESTS
    // ==========================

    @Test
    void testAssignTreatment() {
        PatientEntity p = createPatient(200);
        RoomEntity r = createRoom(300);
        ProceduresEntity proc = createProcedure(500);
        StayEntity stay = createStay(p, r, 600);

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(p.getSsn());
        dto.setProcedureId(proc.getCode());
        dto.setStayId(stay.getStayId());
        dto.setDateUndergoes(LocalDateTime.now().plusSeconds(1));

        assertThat(undergoesService.assignTreatment(dto)).isNotNull();
    }

    @Test
    void testAssignTreatment_Duplicate() {
        PatientEntity p = createPatient(201);
        RoomEntity r = createRoom(301);
        ProceduresEntity proc = createProcedure(501);
        StayEntity stay = createStay(p, r, 601);

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(p.getSsn());
        dto.setProcedureId(proc.getCode());
        dto.setStayId(stay.getStayId());
        dto.setDateUndergoes(LocalDateTime.now().plusSeconds(2));

        undergoesService.assignTreatment(dto);

        assertThrows(DuplicateResourceException.class,
                () -> undergoesService.assignTreatment(dto));
    }

    @Test
    void testGetAllTreatments() {
        PatientEntity p = createPatient(202);
        RoomEntity r = createRoom(302);
        ProceduresEntity proc = createProcedure(502);
        StayEntity stay = createStay(p, r, 602);

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(p.getSsn());
        dto.setProcedureId(proc.getCode());
        dto.setStayId(stay.getStayId());
        dto.setDateUndergoes(LocalDateTime.now().plusSeconds(3));

        undergoesService.assignTreatment(dto);

        assertThat(undergoesService.getAllTreatments()).isNotEmpty();
    }

    @Test
    void testGetTreatmentsByPatient() {
        PatientEntity p = createPatient(203);
        RoomEntity r = createRoom(303);
        ProceduresEntity proc = createProcedure(503);
        StayEntity stay = createStay(p, r, 603);

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(p.getSsn());
        dto.setProcedureId(proc.getCode());
        dto.setStayId(stay.getStayId());
        dto.setDateUndergoes(LocalDateTime.now().plusSeconds(4));

        undergoesService.assignTreatment(dto);

        assertThat(undergoesService.getTreatmentByPatient(p.getSsn()))
                .isNotEmpty();
    }
    @Test
    void testAdmitPatient_DuplicateStayId() {

        PatientEntity p = createPatient(300);
        RoomEntity r = createRoom(400);

        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(10);
        dto.setPatientId(p.getSsn());
        dto.setRoomId(r.getRoomNumber());
        dto.setStayStart(LocalDateTime.now());

        // First insert → success
        stayService.admitPatient(dto);

        // Second insert with same ID → should fail
        assertThrows(BadRequestException.class,
                () -> stayService.admitPatient(dto));
    }
}